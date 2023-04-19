package com.mxf.auth.controller;

import com.mxf.auth.pojo.LoginForm;
import com.mxf.auth.service.SysUserLogin;
import com.mxf.auth.service.ValidateCodeService;
import com.mxf.common.security.service.TokenService;
import com.mxf.domain.Result;
import com.mxf.system.api.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("认证接口类")
@RestController
public class AuthController {
    @Autowired
    private SysUserLogin sysUserLogin;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ValidateCodeService validateCodeService;


    /*
    *                   @ApiParam("用户名")@RequestParam("username") String username,
                        @ApiParam("密码") @RequestParam("password") String password,
                        @ApiParam("验证码") @RequestParam("captcha") String captcha,
                        @ApiParam("前端标识") @RequestParam("webId") String webId*/
    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm) {
        try {
            validateCodeService.checkValidateCode(loginForm.getWebId(), loginForm.getCaptcha());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
        Result<User> loginResult = sysUserLogin.login(loginForm.getUsername(), loginForm.getPassword());
        if (!loginResult.isSuccess()) { // 登录失败
            return loginResult;
        }
        User userinfo = loginResult.getData();
        return tokenService.createToken(userinfo);
    }

    @GetMapping("/register")
    public Result register(@RequestBody User user) {
        return Result.success("注册成功");
    }

    @GetMapping("/captcha")
    public Result index(@RequestParam(required = false) String webId) {
        return validateCodeService.createValidateCode(webId);
    }
}
