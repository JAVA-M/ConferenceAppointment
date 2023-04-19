package com.mxf.service;


import com.mxf.domain.AppointmentInfo;
import com.mxf.domain.CheckInfo;
import com.mxf.domain.ConferenceRoomStatistic;
import com.mxf.domain.query.AppointmentQueryCondition;
import com.mxf.domain.vo.AppointmentInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 满兴富
 * @since 2023-01-06
 */
public interface AppointmentInfoService extends IService<AppointmentInfo> {
    // 查询全部预约信息
    List<AppointmentInfoVo> queryAllAppointment();

    // 按需查找
    List<AppointmentInfoVo> queryByCondition(AppointmentQueryCondition queryCondition);

    // 根据预约单号查询预约信息
    AppointmentInfoVo queryByAppointmentId(Integer id);
    // 创建预约
    Boolean createAppointment(AppointmentInfoVo appointmentInfoVo);

    Boolean createAppointment(AppointmentInfo appointmentInfo);

    Boolean createAppointment(AppointmentInfo appointmentInfo, HttpServletRequest request);

    Boolean deleteAppointment(Integer id);

    List<ConferenceRoomStatistic> queryAllAppointInfo();
    ConferenceRoomStatistic queryAppointInfoById(int id);

    // 查询全部预约总数、服务人次
    List<Integer> queryTotalStatistic();

    List<AppointmentInfoVo> queryUserAppointments(Integer id);

    List<AppointmentInfoVo> queryUserAppointments(Integer id, HttpServletRequest request);

    Boolean updateAppointment(AppointmentInfoVo vo);

    long queryAllAppointmentCount();

    List<AppointmentInfoVo> queryAllUnCheckAppoint();

    boolean insertCheckInfo(CheckInfo info);

    List<AppointmentInfoVo> queryWaitEvaluate(Integer id);

    List<AppointmentInfoVo> queryWaitEvaluate(Integer id, HttpServletRequest request);

    boolean updateAppointStatus(Integer appointId, Integer roomId, Float score);

    void exportUserAppointInfo(Integer id, HttpServletResponse response);
}
