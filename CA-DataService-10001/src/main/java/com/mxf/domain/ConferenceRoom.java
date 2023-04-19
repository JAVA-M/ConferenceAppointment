package com.mxf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 满兴富
 * @since 2022-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ConferenceRoom对象", description="")
public class ConferenceRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会议室名称")
    private String roomName;

    @ApiModelProperty(value = "会议室类型")
    private Integer roomTypeId;

    @ApiModelProperty(value = "清洁状态")
    private Integer cleanStatus;

    @ApiModelProperty(value = "使用状态")
    private Integer status;


}
