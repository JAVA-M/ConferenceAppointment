package com.mxf.service;

import com.mxf.domain.ConferenceRoom;
import com.mxf.domain.ConferenceRoomType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mxf.domain.query.CRQueryCondition;
import com.mxf.domain.query.ConferenceRoomQuery;
import com.mxf.domain.vo.ConferenceRoomVo;


import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 满兴富
 * @since 2022-12-13
 */
public interface ConferenceRoomService extends IService<ConferenceRoom> {
    /*ConferenceRoomVo queryById(@Param("id") int id);

    List<ConferenceRoomVo> queryAll();

    List<ConferenceRoomVo> queryByCondition(CRQueryCondition queryCondition);*/


    // 查询单个会议室信息
    ConferenceRoomVo queryById(int id);

    // 查询全部会议室信息
    List<ConferenceRoomVo> queryAll();

    int queryCaCount();

    // 根据人数查询符合条件的全部会议室
    List<ConferenceRoomVo> queryByCount(Integer count);

    List<ConferenceRoomVo> queryByCondition(CRQueryCondition queryCondition);

    // 查询全部会议室类型信息
    List<ConferenceRoomType> queryAllTypes();

    // 更新会议室信息
    boolean updateRoomInfo(ConferenceRoomVo roomVo);

    boolean insertConferenceRoom(ConferenceRoomVo roomVo);

    boolean deleteConferenceRoom(Integer id);

    // 同步 Mysql 中的数据到 Redis中
    boolean syncMySqlToRedis();

    // 根据ID查缓存
    ConferenceRoomVo queryRedisById(int id);
    //查询所有缓存的 ConferenceRoomVo 对象
    List<ConferenceRoomVo> queryRedisAll();

    // 更新一年当中某一天的某个会议室的某个时段的预约状态
    boolean updateConferenceRoomAppointStatus(int roomId, int day);

    // 获取一年当中某一天的某个会议室预约状态
    List<Boolean> queryConferenceRoomAppointStatus(int roomId, int day);
    List<Boolean> queryConferenceRoomAppointStatus(int roomId, Date day);

    // 按需查找符合条件的会议室
    List<ConferenceRoomVo> queryByAppointQuery(ConferenceRoomQuery queryCondition);
}
