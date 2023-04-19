package com.mxf.common.rabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author manxingfu
 * @date 2023/4/6
 */
@Configuration
public class MqConfiguration {

    // @Bean
    public RabbitTemplate localRabbitTemplate(RabbitTemplate rabbitTemplate) {
        // 设置消息确认回调函数
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println(correlationData);
            if (ack) {
                System.out.println("消息发送成功：" + correlationData.getId());
            } else {
                System.out.println("消息发送失败：" + cause);
            }
        });
        // 设置消息返回回调函数，处理一些不可路由的消息
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> System.out.println("不可路由的消息：" + message));

        return rabbitTemplate;
    }
}
