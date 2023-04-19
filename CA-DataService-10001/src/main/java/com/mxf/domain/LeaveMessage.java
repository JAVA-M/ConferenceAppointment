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
 * 留言实体类
 * </p>
 *
 * @author 满兴富
 * @since 2023-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="LeaveMessage对象", description="")
public class LeaveMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "留言内容")
    private String message;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "头像URL")
    private String avatar;

    @ApiModelProperty(value = "IP")
    private String ip;

    @ApiModelProperty(value = "地址")
    private String location;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "操作系统")
    private String osName;

    @ApiModelProperty(value = "留言是否被处理")
    private int isHandle;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
