package com.mxf.controller;


import com.mxf.domain.Result;
import com.mxf.domain.User;
import com.mxf.domain.vo.UserVo;
import com.mxf.service.UserService;
import com.mxf.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author manxingfu
 * @date 2022/12/14
 */
@RequestMapping("/userinfo")
@RestController
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("/getInfo")
    public Result getPersonalInfo(HttpServletRequest request) {

        return Result.success(userService.queryAdminUserByToken(request));
    }

    @GetMapping("/getUserVoInfo")
    public Result<UserVo> getUserVoInfo(HttpServletRequest request) {

        return Result.success(userService.queryAdminUserVoByToken(request));
    }

    @GetMapping("/admin/queryByName")
    public Result<User> queryAdminUserByName(String username) {
        logger.info("username: " + username);
        return Result.success(userService.queryAdminUserByName(username));
    }

    @GetMapping({"/admin/all", "/admin/list"})
    public Result queryAll() {
        return Result.success(userService.queryAllAdminUser());
    }

    @GetMapping("/admin/vo/all")
    public Result queryAllUserVo(){
        return Result.success(userService.queryAllVoUser());
    }

    @PostMapping("/admin/insert")
    public Result insertAdminUser(@RequestBody User user) {
        logger.info("前端传来的参数：" + user.toString());
        // logger.info(JSONObject.parseObject(adminUser.toJSONString(), AdminUser.class).toString());
        return Result.success(userService.insertAdminUser(user));
    }

    @GetMapping("/admin/queryNameRepeat")
    public Result queryNameIsRepeat(String username){
        return Result.success(userService.queryNameIsRepeat(username)); // 用户名重复就返回true，否则返回false
    }
}
