package com.mxf.domain.vo;

import com.mxf.domain.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="AdminUser视图层对象", description="")
public class UserVo implements Serializable {

    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "角色")
    private Role role;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
