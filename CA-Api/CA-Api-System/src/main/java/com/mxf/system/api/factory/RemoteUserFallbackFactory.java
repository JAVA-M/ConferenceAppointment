package com.mxf.system.api.factory;

import com.mxf.domain.Result;
import com.mxf.system.api.RemoteUserService;
import com.mxf.system.api.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    private static final Logger logger = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);
    @Override
    public RemoteUserService create(Throwable cause) {
        logger.error("远程用户服务调用是失败:{}", cause.getMessage());
        return new RemoteUserService() {
            @Override
            public Result queryUserInfoByName(String username) {
                return Result.failure("获取用户失败: " + cause.getMessage());
            }

            @Override
            public Result insertAdminUser(User user) {
                return Result.failure("用户注册失败: " + cause.getMessage());
            }
        };
    }
}
