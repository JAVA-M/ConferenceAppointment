package com.mxf.common.rabbitmq.service;

import com.mxf.common.rabbitmq.dto.MqMessageDTO;

/**
 * @author manxingfu
 * @date 2023/4/4
 */
public interface MqProducerService {
    void distributeMsg(String exchange, String routeKey, MqMessageDTO<?> message);
}
