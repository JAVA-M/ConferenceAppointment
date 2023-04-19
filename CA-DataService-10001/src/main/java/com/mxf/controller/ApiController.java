package com.mxf.controller;

import com.mxf.domain.Result;
import com.mxf.domain.User;
import com.mxf.domain.LeaveMessage;

import com.mxf.domain.query.ConferenceRoomQuery;
import com.mxf.service.*;
import com.mxf.service.utils.VerificationService;
import com.mxf.web.controller.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * /api/** 下面的接口为公共接口，无需身份验证
 * @author manxingfu
 * @date 2022/12/16
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ConferenceRoomService conferenceService;

    @Autowired
    private AppointmentInfoService appointmentService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private LeaveMessageService leaveMessageService;

    @RequestMapping("/hi")
    public Result hi() {
        logger.info("被调用一次");
        return Result.success(applicationName + " say: hello client");
    }

    @GetMapping("/server/info")
    public Result queryServerInfo() {
        return apiService.querySystemInfo();
    }

    @GetMapping("/role/all")
    public Result queryAllRoles() {
        return Result.success(roleService.queryAllRole());
    }

    @PostMapping("/img/upload")
    public Result uploadImage(@RequestParam("file")  MultipartFile file) {
        return apiService.uploadFile(file);
    }

    @GetMapping("/user/queryNameRepeat")
    public Result queryNameIsRepeat(String username){
        return Result.success(userService.queryNameIsRepeat(username)); // 用户名重复就返回true，否则返回false
    }

    @PostMapping("/user/insert")
    public Result insertAdminUser(@RequestBody User user) {
        logger.info("前端传来的参数：" + user.toString());
        // log.info(JSONObject.parseObject(adminUser.toJSONString(), AdminUser.class).toString());
        return Result.success(userService.insertAdminUser(user));
    }

    @RequestMapping("/room/all")
    public Result queryAll() {
        return Result.success(conferenceService.queryAll());
    }

    @RequestMapping("/room/condition")
    public Result queryRoomByConditional(@RequestBody ConferenceRoomQuery query) {
        // logger.info("首页查询条件: " + query.toString());
        return Result.success(conferenceService.queryByAppointQuery(query));
    }

    @GetMapping("/room/queryById")
    public Result queryById(int id) {
        return Result.success(conferenceService.queryById(id));
    }

    @GetMapping("/room/appointStatus")
    public Result queryRoomAppointStatus(int roomId, @RequestParam(required = false)Long timeStamp) {
        Date date = new Date();
        if (timeStamp != null) {
            date = new Date(timeStamp);
        }
        // logger.info("预约查询时间: " + date);
        return Result.success(conferenceService.queryConferenceRoomAppointStatus(roomId, date));
    }

    @GetMapping("/appoint/statistic/all")
    public Result queryAllAppointmentInfo() {
        return Result.success(appointmentService.queryAllAppointInfo());
    }

    @GetMapping("/appoint/statistic/queryById")
    public Result queryAppointmentInfoById(Integer id) {
        // logger.info("查询会议室ID：" + id);
        return Result.success(appointmentService.queryAppointInfoById(id));
    }

    @GetMapping("/appoint/statistic/total")
    public Result queryTotalData() {
        return Result.success(appointmentService.queryTotalStatistic());
    }

    // 首页查询会议室的公共接口
    @GetMapping("/appoint/query/condition")
    public Result queryByAppointQuery(@RequestBody ConferenceRoomQuery query) {
        logger.info("首页查询条件: " + query);
        return Result.success(conferenceService.queryByAppointQuery(query));
    }

    @GetMapping("/type/allTypes")
    public Result queryAllTypes() {
        return Result.success(conferenceService.queryAllTypes());
    }

    @GetMapping("/room/size")
    public Result queryConferenceRoomNum() {
        return Result.success(conferenceService.queryCaCount());
    }

    @GetMapping("/refresh/verifyCode")
    public Result refreshVerifyCode(String webId) {
        return verificationService.generatorVerifyCode(webId);
    }

    @GetMapping("/message/leaveMessage/all")
    public Result queryAllLeaveMessage(@RequestParam("current") int current, @RequestParam("pageSize") int pageSize, @RequestParam("timeOrder") boolean timeOrder) {
        return Result.success(leaveMessageService.queryAllLeavingMessage(current, pageSize, timeOrder));
    }

    @GetMapping("/message/leaveMessage/size")
    public Result<Integer> queryLeaveMessageSize() {
        return Result.success(leaveMessageService.list().size());
    }

    @PostMapping("/message/leaveMessage/insert")
    public Result insertLeaveMessage(@RequestBody LeaveMessage leaveMessage) {
        return leaveMessageService.insertLeavingMessage(leaveMessage) ? Result.success("插入成功!") : Result.success("插入失败!");
    }
}
