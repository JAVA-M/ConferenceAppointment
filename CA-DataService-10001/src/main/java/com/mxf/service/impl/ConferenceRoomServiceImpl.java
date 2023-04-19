package com.mxf.service.impl;

import com.mxf.constants.RedisConstants;
import com.mxf.domain.ConferenceRoom;
import com.mxf.domain.ConferenceRoomType;
import com.mxf.domain.query.CRQueryCondition;
import com.mxf.domain.query.ConferenceRoomQuery;
import com.mxf.domain.vo.ConferenceRoomVo;
import com.mxf.mapper.ConferenceRoomMapper;
import com.mxf.service.ConferenceRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxf.service.ConferenceRoomTypeService;
import com.mxf.service.RedisService;
import com.mxf.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 满兴富
 * @since 2022-12-13
 */
@Service
public class ConferenceRoomServiceImpl extends ServiceImpl<ConferenceRoomMapper, ConferenceRoom> implements ConferenceRoomService {

    Logger logger = LoggerFactory.getLogger(ConferenceRoomServiceImpl.class);

    @Autowired
    private ConferenceRoomMapper conferenceRoomMapper;

    @Autowired
    private ConferenceRoomTypeService typeService;

    @Autowired
    private RedisService redisService;

    @Override
    public ConferenceRoomVo queryById(int id) {
        return conferenceRoomMapper.queryById(id);
    }

    @Override
    public List<ConferenceRoomVo> queryAll() {
        /*List<ConferenceRoomVo> result = service.queryAll();
        logger.info("查询结果：");
        for (ConferenceRoomVo vo : result) {
            System.out.println(vo.toString());
        }*/
        return conferenceRoomMapper.queryAll();
    }

    @Override
    public int queryCaCount() {
        return Math.toIntExact(conferenceRoomMapper.selectCount(null));
    }

    @Override
    public List<ConferenceRoomVo> queryByCount(Integer count) {

        return conferenceRoomMapper.queryByCount(count);
    }

    @Override
    public List<ConferenceRoomVo> queryByCondition(CRQueryCondition queryCondition) {

        return conferenceRoomMapper.queryByCondition(queryCondition);
    }

    @Override
    public List<ConferenceRoomType> queryAllTypes() {
        return typeService.list();
    }

    @Override
    public boolean updateRoomInfo(ConferenceRoomVo roomVo) {
        ConferenceRoom room = new ConferenceRoom();
        room.setId(roomVo.getId())
                .setRoomName(roomVo.getRoomName())
                .setRoomTypeId(roomVo.getRoomType().getId())
                .setStatus(roomVo.getStatus())
                .setCleanStatus(roomVo.getCleanStatus());

        return conferenceRoomMapper.updateById(room) > 0;
    }

    @Override
    public boolean insertConferenceRoom(ConferenceRoomVo roomVo) {
        ConferenceRoom room = new ConferenceRoom();
        room.setRoomName(roomVo.getRoomName())
                .setRoomTypeId(roomVo.getRoomType().getId())
                .setStatus(roomVo.getStatus())
                .setCleanStatus(roomVo.getCleanStatus());

        return conferenceRoomMapper.insert(room) > 0;
    }

    @Override
    public boolean deleteConferenceRoom(Integer id) {
        return conferenceRoomMapper.deleteById(id) > 0;
    }

    @Override
    public boolean syncMySqlToRedis() {
        for (ConferenceRoomVo vo : queryAll()) {
            redisService.setCacheObject("room_" + vo.getId(), vo);
        }
        /*ConferenceRoomVo vo = queryById(2);
        redisService.setCacheObject("room_" + vo.getId(), vo);*/
        /*ConferenceRoomVo room = redisService.getCacheObject("room_2");
        logger.info("获取的对象: " + room);*/
        return true;
    }

    @Override
    public ConferenceRoomVo queryRedisById(int id) {
        return redisService.getCacheObject("room_" + id);
    }

    @Override
    public List<ConferenceRoomVo> queryRedisAll() {
        List<ConferenceRoomVo> list = new ArrayList<>();
        List<String> names = redisService.getCacheList("all_room_names");
        for (String name : names) {
            list.add(redisService.getCacheObject(name));
        }

        if (list.size() == 0) {
            list = conferenceRoomMapper.queryAll();
        }

        return list;
    }

    @Override
    public boolean updateConferenceRoomAppointStatus(int roomId, int day) {
        day = (day - 1) * 7;
        return redisService.setBitMap(RedisConstants.REDIS_ROOM_APPOINT_STATUS + roomId, day, !redisService.getBitMap(RedisConstants.REDIS_ROOM_APPOINT_STATUS + roomId, day));
    }

    @Override
    public List<Boolean> queryConferenceRoomAppointStatus(int roomId, int day) {
        // logger.info("当前日期为一年当中的第" + day +"天");
        day = (day - 1) * 7;
        return redisService.getRangeBitMap(RedisConstants.REDIS_ROOM_APPOINT_STATUS + roomId, day, day + 6);
    }

    @Override
    public List<Boolean> queryConferenceRoomAppointStatus(int roomId, Date day) {
        return queryConferenceRoomAppointStatus(roomId, DateUtils.daysOfYear(day));
    }

    @Override
    public List<ConferenceRoomVo> queryByAppointQuery(ConferenceRoomQuery conferenceRoomQuery) {
        if (conferenceRoomQuery.getRoomName() != null && !conferenceRoomQuery.getRoomName().equals("")) {
            conferenceRoomQuery.setRoomName("%" + conferenceRoomQuery.getRoomName() + "%");
        }
        conferenceRoomQuery.setPageNo((conferenceRoomQuery.getPageNo() - 1) * conferenceRoomQuery.getPageSize());
        return conferenceRoomMapper.queryByAppointQuery(conferenceRoomQuery);
    }
}
