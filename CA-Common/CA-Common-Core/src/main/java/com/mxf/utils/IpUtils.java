package com.mxf.utils;

// import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author manxingfu
 * @date 2023/2/13
 */
public class IpUtils {

    /**
     * 获取IP地址
     *
     * @return 本地IP地址
     */
    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return "127.0.0.1";
    }

    /**
     * 获取主机名
     *
     * @return 本地主机名
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return "未知";
    }

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关
     *
     * @param checkString 被检测的字符串
     * @return 是否未知
     */
    public static boolean isUnknown(String checkString) {
        // return StringUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
        return checkString.isEmpty() || "unknown".equalsIgnoreCase(checkString);
    }
}
