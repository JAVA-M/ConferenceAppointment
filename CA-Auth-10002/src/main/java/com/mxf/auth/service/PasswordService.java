package com.mxf.auth.service;

import com.mxf.constants.RedisConstants;
import com.mxf.exception.ServiceException;
import com.mxf.service.RedisService;
import com.mxf.system.api.domain.User;
import com.mxf.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author manxingfu
 * @date 2023/3/13
 */
@Service
public class PasswordService {

    @Autowired
    private RedisService redisService;

    public String getCacheErrorCountKey(String username) {
        return RedisConstants.PWD_ERR_CNT_KEY + username;
    }

    public void validate(User user, String password) {
        String username = user.getUsername();
        Integer retryCount = redisService.getCacheObject(getCacheErrorCountKey(username));
        if (retryCount == null) {
            retryCount = 0;
        }
        if (retryCount >= RedisConstants.PASSWORD_MAX_RETRY_COUNT) {
            throw new ServiceException(String.format("密码输入错误%s次，帐户锁定%s分钟", RedisConstants.PASSWORD_MAX_RETRY_COUNT, RedisConstants.PASSWORD_LOCK_TIME));
        }
        if (!match(user.getPassword(), password)) {
            retryCount++;
            redisService.setCacheObject(getCacheErrorCountKey(username), retryCount, RedisConstants.PASSWORD_LOCK_TIME, TimeUnit.MINUTES);
            throw new ServiceException("用户密码错误!");
        } else {
            clearLoginRecordCache(username);
        }
    }

    /**
     * 原始密码和加密后的密码进行验证
     *
     * @param encodePassword 加密后的密码
     * @param rawPassword    原始密码
     * @return 密码是否一致
     */
    public boolean match(String encodePassword, String rawPassword) {
        return SecurityUtils.matchesPassword(rawPassword, encodePassword);
    }

    public void clearLoginRecordCache(String loginName) {
        if (redisService.hasKey(getCacheErrorCountKey(loginName))) {
            redisService.deleteObject(getCacheErrorCountKey(loginName));
        }
    }
}
