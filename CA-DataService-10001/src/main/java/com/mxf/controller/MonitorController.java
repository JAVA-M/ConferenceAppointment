package com.mxf.controller;

import com.mxf.domain.Result;
import com.mxf.service.MonitorService;
import com.mxf.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author manxingfu
 * @date 2023/2/13
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController extends BaseController {
    @Autowired
    private MonitorService monitorService;

    @GetMapping("/server/info")
    public Result queryServerInfo() {
        return Result.success(monitorService.querySystemInfo());
    }

    @GetMapping("/cache/info")
    public Result queryRedisCacheInfo() {
        return Result.success(monitorService.queryCacheInfo());
    }
}
