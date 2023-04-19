package com.mxf.auth.service;

import com.mxf.constants.UserConstants;
import com.mxf.domain.Result;
import com.mxf.system.api.RemoteUserService;
import com.mxf.system.api.domain.User;
import com.mxf.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserLogin {
    @Autowired
    private RemoteUserService userService;

    @Autowired
    private PasswordService passwordService;

    public Result queryUserInfo(String username) {
        return userService.queryUserInfoByName(username);
    }

    public Result<User> login(String username, String password) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Result.failure("用户名或密码不能为空");
        }

        if (username.length() < UserConstants.USERNAME_MIN_LENGTH || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            return Result.failure("用户名过短或过长");
        }

        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            return Result.failure("密码过短过长");
        }

        User loginUser = userService.queryUserInfoByName(username).getData();

        if (loginUser == null) {
            return Result.failure("用户名不存在");
        }

        try {
            passwordService.validate(loginUser, password);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }

        return Result.success(loginUser);
    }

    public Result register(User user) {
        return userService.insertAdminUser(user);
    }
}
