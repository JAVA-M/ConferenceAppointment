package com.mxf.common.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author manxingfu
 * @date 2023/2/23
 */
public class SecurityUtils {
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 给密码加密
     * @param rawPassword
     * @return
     */
    public static String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 判断密码是否相同
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
