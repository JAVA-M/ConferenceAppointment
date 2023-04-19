package com.mxf.service.impl;


import com.mxf.domain.server.Server;
import com.mxf.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author manxingfu
 * @date 2023/2/13
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    @Qualifier("redisTemplateJson")
    private RedisTemplate redisTemplate;

    @Override
    public Server querySystemInfo() {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return server;
    }

    @Override
    public Map<String, Object> queryCacheInfo() {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());

        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            Map<String, String> data = new HashMap<>(2);
            String property = commandStats.getProperty(key);
            data.put("name", key.substring(8));
            // data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
            data.put("value", property);
            pieList.add(data);
        });
        result.put("commandStats", pieList);
        return result;
    }
}
