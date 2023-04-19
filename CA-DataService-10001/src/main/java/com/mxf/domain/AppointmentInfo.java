package com.mxf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mxf.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 满兴富
 * @since 2023-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AppointmentInfo对象", description="")
@Accessors(chain = true)
public class AppointmentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "预约单号")
    @ApiModelProperty(value = "预约单号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "会议主题")
    @ApiModelProperty(value = "会议主题")
    private String conferenceSubject;

    @Excel(name = "参会人数")
    @ApiModelProperty(value = "参会人数")
    private Integer participationNums;

    @Excel(name = "预约人ID")
    @ApiModelProperty(value = "预约用户的ID")
    private Integer appointmentUserId;

    @Excel(name = "预约会议室的ID")
    @ApiModelProperty(value = "预约的会议室ID")
    private Integer appointmentRoomId;

    @Excel(name = "会议开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "会议开始时间")
    private Date appointmentStartTime;

    @Excel(name = "会议结束时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "会议结束时间")
    private Date appointmentEndTime;

    @Excel(name = "预约创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "预约创建时间")
    private Date appointmentCreateTime;

    @Excel(name = "预约状态", readConverterExp = "0=待审核,1=预约成功,2=预约失败,3=会议进行,4=预约完成,5=取消预约,6=待评价")
    @ApiModelProperty(value = "预约状态{0: 待审核, 1: 预约成功, 2: 预约失败, 3:会议进行, 4:预约完成, 5:取消预约, 6:待评价}")
    private Integer appointmentStatus;


}
