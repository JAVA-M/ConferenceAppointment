package com.mxf.controller;

import com.alibaba.fastjson.JSONObject;
import com.mxf.domain.Result;
import com.mxf.domain.query.CRQueryCondition;
import com.mxf.domain.vo.ConferenceRoomVo;
import com.mxf.service.ConferenceRoomService;
import com.mxf.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author manxingfu
 * @date 2023/1/5
 */
@RestController
@RequestMapping("/room")
public class ConferenceRoomController extends BaseController {
    @Autowired
    private ConferenceRoomService conferenceService;

    @GetMapping("/queryById")
    public Result queryById(int id) {
        return Result.success(conferenceService.queryById(id));
    }

    // 根据查询条件，查询符合的所有会议室
    @RequestMapping("/all")
    public Result queryAll(@RequestBody CRQueryCondition queryCondition) {
        logger.info("查询条件：" + queryCondition);
        // return conferenceService.queryAll();
        return Result.success(conferenceService.queryByCondition(queryCondition));
    }

    // 查询全部会议室
    @RequestMapping("/all2")
    public Result queryAll() {
        return Result.success( conferenceService.queryAll());
    }

    @GetMapping("/allTypes")
    public Result queryAllTypes() {
        return Result.success(conferenceService.queryAllTypes());
    }

    @PostMapping("/update")
    public Result updateConferenceRoom(@RequestBody ConferenceRoomVo roomVo) {
        return Result.success(conferenceService.updateRoomInfo(roomVo));
    }

    @PostMapping("/insert")
    public Result insertConferenceRoom(@RequestBody ConferenceRoomVo roomVo) {
        return Result.success(conferenceService.insertConferenceRoom(roomVo));
    }

    @PostMapping("/del")
    public Result insertConferenceRoom(@RequestBody JSONObject json) {

        // logger.info("删除的RoomID: " + json.getInteger("id"));
        return Result.success(conferenceService.deleteConferenceRoom(json.getInteger("id")));
    }

    @GetMapping("/queryByCounts")
    public Result queryByCounts(Integer count) {
        // logger.info("count: " + count);
        return Result.success(conferenceService.queryByCount(count));
    }

    @GetMapping("/redis")
    public Result sync() {
        return Result.success(conferenceService.syncMySqlToRedis());
    }

    @GetMapping("/appointStatus")
    public Result queryRoomAppointStatus(int roomId,@RequestParam(required = false)Long timeStamp) {
        Date date = new Date();
        if (timeStamp != null) {
            date = new Date(timeStamp);
        }
        // logger.info("预约查询时间: " + date);
        return Result.success(conferenceService.queryConferenceRoomAppointStatus(roomId, date));
    }

}
