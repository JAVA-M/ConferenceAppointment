package com.mxf.common.rabbitmq.service.impl.rabbitmq;

import com.alibaba.fastjson2.JSON;
import com.mxf.common.rabbitmq.constants.RabbitMqConstants;
import com.mxf.common.rabbitmq.dto.MqMessageDTO;
import com.mxf.common.rabbitmq.service.MqConsumerService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @author manxingfu
 * @date 2023/4/4
 */
@Primary
@Service
public class RabbitMqConsumerServiceImpl implements MqConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqConsumerServiceImpl.class);
    @Override
    @RabbitListener(queues = RabbitMqConstants.CANAL_FOR_REDIS_QUEUE)
    public void consumerCanalMsg(String msg, Channel channel, Message message) {
        try {
            // 执行逻辑操作
            MqMessageDTO<String> messageDTO = JSON.parseObject(msg, MqMessageDTO.class);
            logger.info("canal-redis队列接收到一条消息:{}", messageDTO.getData());
            // 消息幂等性检查
            String uuid = messageDTO.getUuid();
            //在 Redis 中维护一个set集合，当消息的唯一标识(deliveryTag/uuid)不存在就代表还没处理，就进行正常的业务逻辑处理，处理完成后就将该uuid加入set

            // 操作正常完成才算消费成功，同时手动确认
            // deliveryTag 参数表示消息的唯一标识
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {
                // 发生异常时Nack不确认消息，且将该条消息重新入队，再次尝试消费该条消息
                logger.warn("消费消息时出现异常:{}", e.getMessage());
                /**
                 * 在使用 RabbitMQ 时，当 Java 客户端调用 channel.basicNack(deliveryTag, false, false) 方法时，对应的消息将被标记为“不确认”，
                 * 即这条消息没有被正常处理，需要重新排队。但是，由于在 basicNack 调用中的第二个参数为 false，表示拒绝当前消息，并requeue标志为false，
                 * 就是消息在拒绝后不重新入队列而是直接丢弃，第三个参数同样为false，表示仅拒绝一条消息，所以这种情况下队列中的该条消息虽然没有被消费掉，
                 * 但也不会再次被重新投递到队列中，而是直接被丢弃。因此，在使用 basicNack 方法时，如果需要将消息重新放回队列中，需要将第三个参数设置为 true，
                 * 这样 RabbitMQ 就能够将该消息重新放回队列中，等待下一次重新投递。
                 */
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
