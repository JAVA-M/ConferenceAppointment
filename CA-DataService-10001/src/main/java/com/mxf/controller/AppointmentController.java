package com.mxf.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mxf.domain.Result;
import com.mxf.domain.AppointmentInfo;
import com.mxf.domain.CheckInfo;
import com.mxf.domain.query.AppointmentQueryCondition;
import com.mxf.domain.vo.AppointmentInfoVo;
import com.mxf.service.AppointmentInfoService;
import com.mxf.utils.JWTUtils;
import com.mxf.utils.poi.ExcelUtil;
import com.mxf.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author manxingfu
 * @date 2023/1/9
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentController extends BaseController {
    @Autowired
    private AppointmentInfoService appointmentService;

    @GetMapping("/all")
    public Result queryAllAppointments() {
        return Result.success(appointmentService.queryAllAppointment());
    }

    @PostMapping("/queryByCondition")
    public Result queryByCondition(@RequestBody AppointmentQueryCondition queryCondition) {
        logger.info("查询条件: " + queryCondition);
        return Result.success(appointmentService.queryByCondition(queryCondition));
    }

    @GetMapping("/queryById")
    public Result queryAppointmentById(Integer id) {
        return Result.success(appointmentService.queryByAppointmentId(id));
    }

    @PostMapping("/insert")
    public Result insertAppointment(@RequestBody AppointmentInfo appointmentInfo, HttpServletRequest request) {
        return appointmentService.createAppointment(appointmentInfo, request) ? Result.success("预约成功") : Result.failure("该时段已被预约请勿重复预约!!!");
    }

    @PostMapping("/del")
    public Result deleteAppointment(@RequestBody JSONObject json) {
        return Result.success(appointmentService.deleteAppointment(json.getInteger("id")));
    }

    @GetMapping("/queryByUserId")
    public Result queryUserAppointments(@RequestParam(required=false) Integer id, HttpServletRequest request) {
        return Result.success(appointmentService.queryUserAppointments(id, request));
    }

    @PostMapping("/update")
    public Result updateAppointInfo(@RequestBody AppointmentInfoVo vo) {
        logger.info("更新的预约对象: " + vo);
        return Result.success(appointmentService.updateAppointment(vo));
    }

    @GetMapping("/queryAllNums")
    public Result queryAllAppointmentCount() {
        return Result.success(appointmentService.queryAllAppointmentCount());
    }

    @GetMapping("/allUncheck")
    public Result queryAllUnCheckAppoint() {
        return Result.success(appointmentService.queryAllUnCheckAppoint());
    }

    @PostMapping("/insert/checkInfo")
    public Result insertCheckInfo(@RequestBody CheckInfo info) {
        return Result.success(appointmentService.insertCheckInfo(info));
    }

    @GetMapping("/waitEvaluate")
    public Result queryAllWaitEvaluate(@RequestParam(required = false)Integer id, HttpServletRequest request) {
        return Result.success(appointmentService.queryWaitEvaluate(id, request));
    }

    @PostMapping("/updateAppointStatus")
    public Result updateAppointStatus(@RequestBody JSONObject json) {
        // logger.info("参数[appointId: %s, roomId: %s, score: %s]", json.getString("appointId"), json.getString("roomId"), json.getString("score"));
        // System.out.printf("参数[appointId: %s, roomId: %s, score: %s]", json.getString("appointId"), json.getString("roomId"), json.getString("score"));
        return Result.success(appointmentService.updateAppointStatus(json.getInteger("appointId"), json.getInteger("roomId"), json.getFloat("score")));
    }

    @PostMapping("/export")
    public void exportUserAppointInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody(required = false) JSONObject json) {
        Integer id = json.getInteger("id");
        if (id == null) {
            id = JWTUtils.parseUserId(request.getHeader("token"));
        }
        appointmentService.exportUserAppointInfo(id, response);
        // return Result.success("成功生成用户的预约信息Excel表!!!");
    }

    @PostMapping("/export2")
    public void exportUserAppointInfo2(HttpServletRequest request, HttpServletResponse response, @RequestBody(required = false) JSONObject json) {
        Integer id = json.getInteger("id");
        if (id == null) {
            id = JWTUtils.parseUserId(request.getHeader("token"));
        }
        QueryWrapper<AppointmentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("appointment_user_id", id);
        List<AppointmentInfo> appointmentInfoList = appointmentService.list(queryWrapper);
        ExcelUtil<AppointmentInfo> excelUtil = new ExcelUtil<>(AppointmentInfo.class);
        excelUtil.exportExcel(response, appointmentInfoList, "用户预约信息汇总");
        logger.info("预约信息Excel导出成功!!!");
        // return Result.success("成功生成用户的预约信息Excel表!!!");
    }
}
