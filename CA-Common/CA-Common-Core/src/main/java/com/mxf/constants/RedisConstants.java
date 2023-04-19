package com.mxf.constants;

/**
 * @author manxingfu
 * @date 2023/2/16
 */
public class RedisConstants {
    public static final String REDIS_ROOM_APPOINT_STATUS = "appoint_status_of_room_";

    public static final String MAX_APPOINT_COUNT = "max_appoint_count";

    public static final String MAX_USE_USER_COUNT = "max_use_user_count";

    /**
     * 密码最大重试次数
     */
    public static final int PASSWORD_MAX_RETRY_COUNT = 5;

    /**
     * 密码锁定时间，默认10（分钟）
     */
    public final static long PASSWORD_LOCK_TIME = 10;

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

    /**
     * 缓存用户信息的前缀key
     */
    public static final String LOGIN_USER_KEY = "login_user:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 会议室锁的前缀key
     */
    public static final String ROOM_LOCK_KEY = "room_lock:";
}
