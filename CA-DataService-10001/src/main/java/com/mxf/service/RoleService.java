package com.mxf.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mxf.domain.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 满兴富
 * @since 2022-12-26
 */
public interface RoleService extends IService<Role> {
    List<Role> queryAllRole();
}
