package com.mxf.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mxf.constants.RedisConstants;
import com.mxf.domain.AppointmentInfo;
import com.mxf.domain.CheckInfo;
import com.mxf.domain.ConferenceRoomStatistic;
import com.mxf.domain.query.AppointmentQueryCondition;
import com.mxf.domain.vo.AppointmentInfoVo;
import com.mxf.enums.AppointStatus;
import com.mxf.mapper.CheckInfoMapper;
import com.mxf.mapper.ConferenceRoomStatisticMapper;
import com.mxf.mapper.AppointmentInfoMapper;
import com.mxf.service.AppointmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxf.service.RedisService;
import com.mxf.utils.JWTUtils;
import com.mxf.utils.RedisUtils;
import com.mxf.utils.poi.ExcelUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 满兴富
 * @since 2023-01-06
 */
@Service
public class AppointmentInfoServiceImpl extends ServiceImpl<AppointmentInfoMapper, AppointmentInfo> implements AppointmentInfoService {

    @Autowired
    private AppointmentInfoMapper appointmentInfoMapper;

    @Autowired
    private ConferenceRoomStatisticMapper statisticMapper;

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedissonClient redissonClient;

    private Logger logger = LoggerFactory.getLogger(AppointmentInfoServiceImpl.class);

    @Override
    public List<AppointmentInfoVo> queryAllAppointment() {
        return appointmentInfoMapper.queryAllAppointment();
    }

    @Override
    public List<AppointmentInfoVo> queryByCondition(AppointmentQueryCondition queryCondition) {
        // 计算分页偏移量
        queryCondition.setPage((queryCondition.getPage() - 1) * queryCondition.getSize());
        return appointmentInfoMapper.queryByCondition(queryCondition);
    }

    @Override
    public AppointmentInfoVo queryByAppointmentId(Integer id) {
        return appointmentInfoMapper.queryByAppointmentId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createAppointment(AppointmentInfoVo appointmentInfoVo) {
        AppointmentInfo appointment = new AppointmentInfo();
        appointment.setConferenceSubject(appointmentInfoVo.getConferenceSubject())
                .setParticipationNums(appointmentInfoVo.getParticipationNums())
                .setAppointmentUserId(appointmentInfoVo.getAppointmentUser().getId())
                .setAppointmentRoomId(appointmentInfoVo.getAppointmentRoom().getId())
                .setAppointmentStartTime(appointmentInfoVo.getAppointmentStartTime())
                .setAppointmentEndTime(appointmentInfoVo.getAppointmentEndTime())
                .setAppointmentStatus(appointmentInfoVo.getAppointmentStatus())
                .setAppointmentCreateTime(new Date());
        return createAppointment(appointment);
    }



    /**
     * 开启事务，当发生异常时回滚，防止预约记录在数据库插入成功，但是redis中记录的预约状态以及统计信息等都未更新，
     * 从而造成同一会议室的同一时段被多个用户同时预约的错误情况
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean createAppointment(AppointmentInfo appointmentInfo) {
        appointmentInfo.setAppointmentCreateTime(new Date());

        int[] offsets = RedisUtils.calculatorOffset(appointmentInfo.getAppointmentStartTime(), appointmentInfo.getAppointmentEndTime());
        // logger.info("记录本次预约的bitMap下标位置: " + Arrays.toString(offsets));
        String statusKey = RedisConstants.REDIS_ROOM_APPOINT_STATUS + appointmentInfo.getAppointmentRoomId();
        // 获取Redisson实现的分布式锁,当且仅当多个用户预约同一会议室的同一时段才会互斥
        RLock lock = redissonClient.getLock(RedisConstants.ROOM_LOCK_KEY + appointmentInfo.getAppointmentRoomId() + "_" + offsets[0]);
        lock.lock();
        boolean result = false;

        try {
            // 检查该次预约的时段，是否已有预约
            for (int offset : offsets) {
                if (redisService.getBitMap(statusKey, offset)) {
                    return false;
                }
            }

            /**
             * 描述：在并发情况下，可能多个线程同时在时段空闲的状态下走到这一步，所以会写入多条信息，造成错误。
             * 解决：所以要采用锁来保证原子性，同一时刻只有一个线程进来，在微服务分布式情况下就要采用分布式锁来保证线程安全
             */

            result = appointmentInfoMapper.insert(appointmentInfo) > 0;
            // 如果预约成功，则要更新缓存
            if (result) {
                redisService.setBitMap(statusKey, offsets, true);

                // 更新统计数据
                ConferenceRoomStatistic roomStatistic = statisticMapper.selectById(appointmentInfo.getAppointmentRoomId());
                roomStatistic.setAppointmentCount(roomStatistic.getAppointmentCount() + 1);
                // 会议室的预约次数+1
                roomStatistic.setUserCount(roomStatistic.getUserCount() + appointmentInfo.getParticipationNums());
                statisticMapper.updateById(roomStatistic);
                // 更新缓存的统计数据
                Integer maxAppoint = redisService.getCacheObject(RedisConstants.MAX_APPOINT_COUNT);
                Integer maxUser = redisService.getCacheObject(RedisConstants.MAX_USE_USER_COUNT);
                if (roomStatistic.getAppointmentCount() > maxAppoint) {
                    redisService.setCacheObject(RedisConstants.MAX_APPOINT_COUNT, roomStatistic.getAppointmentCount());
                }
                if (roomStatistic.getUserCount() > maxUser) {
                    redisService.setCacheObject(RedisConstants.MAX_USE_USER_COUNT, roomStatistic.getUserCount());
                }
            }
        } catch (Exception e) {
            logger.info("创建预约时发生异常: {}", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createAppointment(AppointmentInfo appointmentInfo, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        //
        // logger.info("预约用户的ID: " + verify.getClaim("id").asString());
        appointmentInfo.setAppointmentUserId(Integer.parseInt(verify.getClaim("id").asString()));
        return createAppointment(appointmentInfo);
    }

    @Override
    public Boolean deleteAppointment(Integer id) {
        AppointmentInfo appointmentInfo = appointmentInfoMapper.selectById(id);
        // 修改预约状态为取消预约
        appointmentInfo.setAppointmentStatus(AppointStatus.Cancel.getStatusCode());

        boolean result = appointmentInfoMapper.updateById(appointmentInfo) > 0;
        if(result) {
            int[] offsets = RedisUtils.calculatorOffset(appointmentInfo.getAppointmentStartTime(), appointmentInfo.getAppointmentEndTime());
            logger.info("取消本次预约的下标位置: " + Arrays.toString(offsets));

            redisService.setBitMap(RedisConstants.REDIS_ROOM_APPOINT_STATUS + appointmentInfo.getAppointmentRoomId(), offsets, false);
        }
        return result;
    }

    @Override
    public List<ConferenceRoomStatistic> queryAllAppointInfo() {
        return statisticMapper.selectList(null);
    }

    @Override
    public ConferenceRoomStatistic queryAppointInfoById(int id) {
        return statisticMapper.selectById(id);
    }

    @Override
    public List<Integer> queryTotalStatistic() {
        List<Integer> list = new ArrayList<>();
        list.add(redisService.getCacheObject(RedisConstants.MAX_APPOINT_COUNT));
        list.add(redisService.getCacheObject(RedisConstants.MAX_USE_USER_COUNT));
        return list;
    }

    @Override
    public List<AppointmentInfoVo> queryUserAppointments(Integer id) {
        return appointmentInfoMapper.queryUserAppointments(id);
    }

    @Override
    public List<AppointmentInfoVo> queryUserAppointments(Integer id, HttpServletRequest request) {
        if (id == null) {
            id = JWTUtils.parseUserId(request.getHeader("token"));
        }
        return appointmentInfoMapper.queryUserAppointments(id);
    }

    @Override
    public Boolean updateAppointment(AppointmentInfoVo vo) {
        AppointmentInfo info = new AppointmentInfo();
        info.setId(vo.getId())
                .setConferenceSubject(vo.getConferenceSubject())
                .setParticipationNums(vo.getParticipationNums())
                .setAppointmentUserId(vo.getAppointmentUser().getId())
                .setAppointmentRoomId(vo.getAppointmentRoom().getId())
                .setAppointmentStartTime(vo.getAppointmentStartTime())
                .setAppointmentEndTime(vo.getAppointmentEndTime())
                .setAppointmentCreateTime(vo.getAppointmentCreateTime())
                .setAppointmentStatus(vo.getAppointmentStatus());
        return appointmentInfoMapper.updateById(info) > 0;
    }

    @Override
    public long queryAllAppointmentCount() {
        return appointmentInfoMapper.selectCount(null);
    }

    @Override
    public List<AppointmentInfoVo> queryAllUnCheckAppoint() {
        return appointmentInfoMapper.queryAllUnCheckAppoint();
    }

    @Override
    public boolean insertCheckInfo(CheckInfo info) {
        boolean result = checkInfoMapper.insert(info) > 0;
        if (result) {
            AppointmentInfo appointmentInfo = appointmentInfoMapper.selectById(info.getCheckAppointId());
            // 更新预约的状态
            appointmentInfo.setAppointmentStatus(info.getCheckResult() == 1 ? AppointStatus.Success.getStatusCode() : AppointStatus.Failure.getStatusCode());
            appointmentInfoMapper.updateById(appointmentInfo);
        }
        return result;
    }

    @Override
    public List<AppointmentInfoVo> queryWaitEvaluate(Integer id) {
        return appointmentInfoMapper.queryWaitEvaluate(id);
    }

    @Override
    public List<AppointmentInfoVo> queryWaitEvaluate(Integer id, HttpServletRequest request) {
        if (id == null) {
            id = JWTUtils.parseUserId(request.getHeader("token"));
        }
        return queryWaitEvaluate(id);
    }

    // 如何保证复合操作的原子性（事务）
    @Override
    @Transactional
    public boolean updateAppointStatus(Integer appointId, Integer roomId, Float score) {
        // 更新预约的状态
        boolean status = appointmentInfoMapper.updateAppointStatus(appointId, AppointStatus.Finish.getStatusCode());
        // 更新会议室的评分
        boolean scoreStatus = appointmentInfoMapper.updateConferenceRoomScore(roomId, score);
        return scoreStatus && status;
    }

    @Override
    public void exportUserAppointInfo(Integer id, HttpServletResponse response) {
        QueryWrapper<AppointmentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("appointment_user_id", id);
        List<AppointmentInfo> appointmentInfoList = appointmentInfoMapper.selectList(queryWrapper);

        ExcelUtil<AppointmentInfo> excelUtil = new ExcelUtil<>(AppointmentInfo.class);
        excelUtil.exportExcel(response, appointmentInfoList, "用户预约信息汇总");
    }
}
