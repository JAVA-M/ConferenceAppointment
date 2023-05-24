package com.mxf.chat.service;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.shaded.com.google.errorprone.annotations.Var;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.mxf.chat.config.CustomConfigurator;
import com.mxf.chat.domain.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author manxingfu
 * @date 2023/5/24 12:14
 * @desc
 */
@ServerEndpoint(value = "/websocket", configurator = CustomConfigurator.class)
@Service
public class WebSocketServerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Map<String, Session> sessions = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
       logger.info("{} -> 连接成功", session.getId());
        sessions.put((String) config.getUserProperties().get("userId"), session);
        ChatMessage message = new ChatMessage("101", "欢迎加入聊天室，请自觉遵守聊天室规则，违者将面临封号处理", "管理员", new Date());
        session.getBasicRemote().sendText(JSONObject.toJSONString(message));
    }

    @OnClose
    public void onClose(EndpointConfig config) {
        logger.info("{} -> 断开连接", (String) config.getUserProperties().get("userId"));
        sessions.remove((String) config.getUserProperties().get("userId"));
    }

    @OnMessage
    public void onMessage(String jsonMessage, EndpointConfig config) {
        // logger.info("前端传来的String: {}", jsonMessage);
        ChatMessage message = JSONObject.parseObject(jsonMessage, ChatMessage.class);
        logger.info("[{}] {} 发送了一条消息:{}",simpleDateFormat.format(message.getCreateTime()), message.getUsername(), message.getContent());
        Session recover = sessions.get((String) config.getUserProperties().get("userId"));

        message.setUsername("管理员");
        message.setCreateTime(new Date());
        message.setContent("服务端已收到，正在处理中!");

        try {
            recover.getBasicRemote().sendText(JSONObject.toJSONString(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
