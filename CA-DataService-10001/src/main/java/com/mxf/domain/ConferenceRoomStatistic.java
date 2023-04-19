package com.mxf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author 满兴富
 * @since 2023-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ConferenceRoomStatistic对象", description="")
public class ConferenceRoomStatistic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对应的会议室ID")
    @TableId(value = "conference_room_id", type = IdType.AUTO)
    private Integer conferenceRoomId;

    @ApiModelProperty(value = "会议室被预约的次数")
    private Integer appointmentCount;

    @ApiModelProperty(value = "累计服务人数")
    private Integer userCount;

    @ApiModelProperty(value = "会议室的平均评分")
    private BigDecimal averageScore;

    @ApiModelProperty(value = "会议室面积")
    private Integer roomArea;

    @ApiModelProperty(value = "设备正常率")
    private BigDecimal equipmentNormalRate;


}
