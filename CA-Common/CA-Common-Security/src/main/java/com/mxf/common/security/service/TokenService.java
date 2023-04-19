package com.mxf.common.security.service;

import com.mxf.domain.Result;
import com.mxf.service.RedisService;
import com.mxf.system.api.domain.User;
import com.mxf.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.mxf.constants.RedisConstants.LOGIN_USER_KEY;

/**
 * @author manxingfu
 * @date 2023/3/13
 */
@Service
public class TokenService {

    @Autowired
    private RedisService redisService;

    public Result createToken(User user) {
        if (user == null) return Result.failure("Token生成失败");
        refreshUser(user); // 缓存用户

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("id", user.getId().toString());
        userInfo.put("role", user.getRole().toString());
        userInfo.put("username", user.getUsername());

        String token = JWTUtils.getToken(userInfo);

        return Result.success("登录成功!", token);
    }

    public void refreshUser(User user) {
        redisService.setCacheObject(LOGIN_USER_KEY + user.getUsername(), user, 12L, TimeUnit.HOURS);
    }


}
