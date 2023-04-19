package com.mxf.service.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.mxf.domain.Result;
import com.mxf.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author manxingfu
 * @date 2023/2/28
 */
@Component
public class VerificationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    public Result generatorVerifyCode(String webId) {
        // 定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 40, 5, 4);
        // 图形验证码写出，可以写出到文件，也可以写出到流
        /*try {
            captcha.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        // 获取验证码中的文字内容 缓存2分钟
        redisService.setCacheObject(webId, captcha.getCode(), 120L, TimeUnit.SECONDS);

        // logger.info("生成的验证码: {}", verifyCode);
        return Result.success(captcha.getImageBase64Data());
    }
}
