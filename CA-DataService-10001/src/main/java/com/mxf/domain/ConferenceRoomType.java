package com.mxf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 满兴富
 * @since 2023-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ConferenceRoomType对象", description="")
public class ConferenceRoomType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类型名称")
    private String roomTypeName;

    @ApiModelProperty(value = "会议室面积")
    private BigDecimal roomArea;

    @ApiModelProperty(value = "容纳人数")
    private String roomTypeCapacity;

    @ApiModelProperty(value = "类型描述")
    private String roomTypeDesc;

    @ApiModelProperty(value = "典型封面")
    private String roomCover;

    @ApiModelProperty(value = "创建时间")
    private Date roomTypeCreateTime;


}
