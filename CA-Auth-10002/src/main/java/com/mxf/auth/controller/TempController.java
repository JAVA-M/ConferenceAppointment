package com.mxf.auth.controller;

import com.mxf.common.rabbitmq.constants.RabbitMqConstants;
import com.mxf.common.rabbitmq.dto.MqMessageDTO;
import com.mxf.common.rabbitmq.service.MqConsumerService;
import com.mxf.common.rabbitmq.service.MqProducerService;
import com.mxf.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author manxingfu
 * @date 2023/4/4
 */
@RestController
public class TempController {
    @Autowired
    private MqProducerService producerService;

    @Autowired
    private MqConsumerService consumerService;

    @GetMapping("/create")
    public Result<String> createMsg(@RequestParam("msg") String msg) {
        // 构造消息传输对象
        MqMessageDTO<String> messageDTO = new MqMessageDTO<>("1", msg);
        producerService.distributeMsg(RabbitMqConstants.CANAL_FOR_REDIS_EXCHANGE, RabbitMqConstants.CANAL_FOR_REDIS_ROUTE_KEY, messageDTO);
        return Result.success("发送成功");
    }

    @GetMapping("/create2")
    public Result<String> createMsg2(@RequestParam("msg") String msg) {
        // 构造消息传输对象
        MqMessageDTO<String> messageDTO = new MqMessageDTO<>("1", msg);
        producerService.distributeMsg(RabbitMqConstants.CANAL_FOR_REDIS_EXCHANGE + "1", RabbitMqConstants.CANAL_FOR_REDIS_ROUTE_KEY + "1", messageDTO);
        return Result.success("发送成功");
    }
}
