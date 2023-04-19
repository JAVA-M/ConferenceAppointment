package com.mxf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mxf.constants.RedisConstants;
import com.mxf.domain.User;
import com.mxf.domain.Role;
import com.mxf.domain.vo.UserVo;
import com.mxf.mapper.UserMapper;
import com.mxf.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxf.service.RedisService;
import com.mxf.service.RoleService;
import com.mxf.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 满兴富
 * @since 2022-12-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleService roleService;

    @Override
    public List<UserVo> queryAllVoUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public User queryAdminUserByName(String username) {
        User user = redisService.getCacheObject(username);
        if (user == null) {
            // 查用户
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);
            user = userMapper.selectOne(wrapper);
            if (user == null) {
                return null;
            }
            // 查询角色
            Role role = roleService.getById(user.getRole());

            redisService.setCacheObject(username, user, 7L, TimeUnit.DAYS);
        }

        return user;
    }

    @Override
    public List<User> queryAllAdminUser() {
        return userMapper.selectList(null);
    }

    @Override
    public boolean insertAdminUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean queryNameIsRepeat(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public User queryAdminUserByToken(HttpServletRequest request) {
        User user = redisService.getCacheObject(RedisConstants.LOGIN_USER_KEY + request.getHeader("username"));
        if (user == null) {
            user = userMapper.selectById(JWTUtils.parseUserId(request.getHeader("token")));
        }
        return user;
    }

    @Override
    public UserVo queryAdminUserVoByToken(HttpServletRequest request) {
        return userMapper.queryUserVoById(JWTUtils.parseUserId(request.getHeader("token")));
    }
}
