package com.mxf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 满兴富
 * @since 2023-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CheckInfo对象", description="")
public class CheckInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "审核ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "审核人信息")
    private Integer checkUserId;

    @ApiModelProperty(value = "被审核的预约ID")
    private Integer checkAppointId;

    @ApiModelProperty(value = "审核结果")
    private Integer checkResult;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "审核时间")
    private Date createTime;


}
