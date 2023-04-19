package com.mxf.common.rabbitmq.service.impl.rabbitmq;

import com.alibaba.fastjson2.JSONObject;
import com.mxf.common.rabbitmq.dto.MqMessageDTO;
import com.mxf.common.rabbitmq.service.MqProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author manxingfu
 * @date 2023/4/4
 */
@Primary
@Service
public class RabbitMqProducerServiceImpl implements MqProducerService {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqProducerServiceImpl.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMqProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // 设置消息确认回调函数
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            assert correlationData != null;
            ReturnedMessage message = correlationData.getReturned();
            // logger.info("correlationData: " + message + " exchange: ");
            if (ack) {
                logger.info("消息发送成功: {}", correlationData.getId());
            } else {
                logger.warn("消息发送失败：{}, 尝试重新发送消息", cause);
                 this.rabbitTemplate.convertAndSend(message.getExchange(), message.getRoutingKey(), message.getMessage());
            }
        });

        // 设置消息返回回调函数，处理一些不可路由的消息
        this.rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> logger.warn("不可路由的消息：" + message));
    }

    @Override
    public void distributeMsg(String exchange, String routeKey, MqMessageDTO<?> messageDTO) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        Message message = new Message(JSONObject.toJSONString(messageDTO).getBytes()); // 构建消息
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT); // 开启消息持久化，如果需要的话

        correlationData.setReturned(new ReturnedMessage(message, 0, null, exchange, routeKey));

        rabbitTemplate.convertAndSend(exchange, routeKey, JSONObject.toJSONString(messageDTO), correlationData);
    }
}
