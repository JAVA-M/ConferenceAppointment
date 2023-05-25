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
@ServerEndpoint(value = "/websocket") // , configurator = CustomConfigurator.class
@Service
public class WebSocketServerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final ChatMessage message = new ChatMessage("001", "欢迎加入聊天室，请自觉遵守聊天室规则，违者将面临封号处理", "0", "系统", new Date());

    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session) throws IOException {
        String userId = getUserId(session);
        sessions.put(userId, session);
        logger.info("用户-[{}] -> 连接成功, 目前共[{}]位用户在线", userId, sessions.size());
        message.setCreateTime(new Date());
        session.getBasicRemote().sendText(JSONObject.toJSONString(message));
    }

    @OnClose
    public void onClose(Session session) {
        String userId = getUserId(session);
        sessions.remove(userId);

        logger.info("用户-[{}] -> 断开连接, 目前共[{}]位用户在线", userId, sessions.size());
    }

    @OnMessage
    public void onMessage(String jsonMessage,Session session) {
        // logger.info("前端传来的String: {}", jsonMessage);
        ChatMessage message = JSONObject.parseObject(jsonMessage, ChatMessage.class);
        logger.info("[{}] {} 发送了一条消息:{}",simpleDateFormat.format(message.getCreateTime()), message.getUsername(), message.getContent());
        /*Session recover = sessions.get(getUserId(session));

        message.setUsername("管理员");
        message.setCreateTime(new Date());
        message.setContent("服务端已收到，正在处理中!");

        try {
            recover.getBasicRemote().sendText(JSONObject.toJSONString(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        message.setUserId(getUserId(session));
        for (String id : sessions.keySet()) {
            if (id.equals(getUserId(session))) continue;
            try {
                sessions.get(id).getBasicRemote().sendText(jsonMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getUserId(Session session) {
        return session.getRequestParameterMap().get("userId").get(0);
    }

}
