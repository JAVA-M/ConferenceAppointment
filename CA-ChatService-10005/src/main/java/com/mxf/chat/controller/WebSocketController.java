package com.mxf.chat.controller;

import com.mxf.chat.domain.ChatMessage;
import com.mxf.web.controller.BaseController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author manxingfu
 * @date 2023/5/23 18:50
 * @desc
 */
@RestController
public class WebSocketController extends BaseController {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage handleMessage(ChatMessage message) {
        this.logger.info("[{}] {} 发送了一条消息:{}",simpleDateFormat.format(message.getCreateTime()), message.getUsername(), message.getContent());
        message.setUsername("管理员");
        message.setCreateTime(new Date());
        message.setContent("服务端已收到，正在处理中!");
        return message;
    }
}
