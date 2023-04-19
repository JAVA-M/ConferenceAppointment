package com.mxf.domain.vo;

import com.mxf.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author manxingfu
 * @date 2023/1/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentInfoVo implements Serializable {

    @ApiModelProperty(value = "预约单号")
    private Integer id;

    @ApiModelProperty(value = "会议主题")
    private String conferenceSubject;

    @ApiModelProperty(value = "参会人数")
    private Integer participationNums;

    @ApiModelProperty(value = "预约用户")
    private User appointmentUser;

    @ApiModelProperty(value = "预约的会议室")
    private ConferenceRoomVo appointmentRoom;

    @ApiModelProperty(value = "会议开始时间")
    private Date appointmentStartTime;

    @ApiModelProperty(value = "会议结束时间")
    private Date appointmentEndTime;

    @ApiModelProperty(value = "预约创建时间")
    private Date appointmentCreateTime;

    @ApiModelProperty(value = "预约状态{0: 待审核, 1: 预约成功, 2: 预约失败, 3:会议进行, 4:预约完成}")
    private Integer appointmentStatus;
}
