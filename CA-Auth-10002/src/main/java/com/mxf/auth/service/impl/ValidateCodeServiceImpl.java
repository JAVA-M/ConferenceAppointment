package com.mxf.auth.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.mxf.auth.service.ValidateCodeService;
import com.mxf.constants.RedisConstants;
import com.mxf.domain.Result;
import com.mxf.exception.CaptchaException;
import com.mxf.service.RedisService;
import com.mxf.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author manxingfu
 * @date 2023/3/17
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisService redisService;

    @Override
    public Result createValidateCode(String webId) {
        if (webId == null) {
            webId = IdUtils.fastUUID();
        }
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 40, 5, 4);
        redisService.setCacheObject(RedisConstants.CAPTCHA_CODE_KEY + webId, captcha.getCode(), 3L, TimeUnit.MINUTES);

        HashMap<String, String> map = new HashMap<>();
        map.put("webId", webId);
        map.put("captcha", captcha.getImageBase64Data());
        return Result.success(map);
    }

    @Override
    public void checkValidateCode(String webId, String code) {
        if (code == null || code.trim().equals("")) {
            throw new CaptchaException("验证码不能为空");
        }
        String captcha = redisService.getCacheObject(RedisConstants.CAPTCHA_CODE_KEY + webId);
        if (captcha == null || captcha.equals("")) {
            throw new CaptchaException("验证码已失效");
        }

        if (!captcha.equalsIgnoreCase(code)) {
            throw new CaptchaException("验证码错误");
        }
    }
}
