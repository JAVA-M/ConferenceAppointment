package com.mxf.mapper;


import com.mxf.domain.AppointmentInfo;
import com.mxf.domain.query.AppointmentQueryCondition;
import com.mxf.domain.query.ConferenceRoomQuery;
import com.mxf.domain.vo.AppointmentInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 满兴富
 * @since 2023-01-06
 */
@Mapper
public interface AppointmentInfoMapper extends BaseMapper<AppointmentInfo> {
    // 查询全部预约信息
    List<AppointmentInfoVo> queryAllAppointment();

    // 根据预约单号查询预约信息
    AppointmentInfoVo queryByAppointmentId(@Param("id") Integer id);

    // 按需查找
    List<AppointmentInfoVo> queryByCondition(@Param("queryCondition") AppointmentQueryCondition queryCondition);
    List<AppointmentInfoVo> queryByAppointQuery(@Param("queryCondition") ConferenceRoomQuery queryCondition);

    // 查询用户所有的预约信息
    List<AppointmentInfoVo> queryUserAppointments(@Param("id") Integer id);

    List<AppointmentInfoVo> queryAllUnCheckAppoint();

    // 查询用户待评价的预约
    List<AppointmentInfoVo> queryWaitEvaluate(@Param("id") int id);

    boolean updateAppointStatus(@Param("id") int id,@Param("status") int status);

    boolean updateConferenceRoomScore(@Param("roomId") int roomId, @Param("score") float score);
}
