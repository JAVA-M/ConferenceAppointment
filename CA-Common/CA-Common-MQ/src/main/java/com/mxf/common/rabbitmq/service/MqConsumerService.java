package com.mxf.common.rabbitmq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * @author manxingfu
 * @date 2023/4/4
 */
public interface MqConsumerService {
    void consumerCanalMsg(String msg, Channel channel, Message message);
}
