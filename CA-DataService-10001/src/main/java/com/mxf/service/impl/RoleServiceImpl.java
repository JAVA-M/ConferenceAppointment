package com.mxf.service.impl;

import com.mxf.domain.Role;
import com.mxf.mapper.RoleMapper;
import com.mxf.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 满兴富
 * @since 2022-12-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryAllRole() {
        return roleMapper.selectList(null);
    }
}
