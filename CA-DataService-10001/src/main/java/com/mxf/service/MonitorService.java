package com.mxf.service;


import com.mxf.domain.server.Server;

import java.util.Map;

/**
 * @author manxingfu
 * @date 2023/2/13
 */
public interface MonitorService {
    // 获取服务器信息
    Server querySystemInfo();

    // 获取redis缓存信息
    Map<String, Object> queryCacheInfo();
}
