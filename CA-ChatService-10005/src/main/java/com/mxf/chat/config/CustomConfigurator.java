package com.mxf.chat.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author manxingfu
 * @date 2023/5/24 16:24
 * @desc
 */
public class CustomConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 获取连接头信息
        String clientId = request.getHeaders().get("X-User-Id").get(0);
        // 将连接头信息存储到ServerEndpointConfig中
        sec.getUserProperties().put("userId", clientId);
        super.modifyHandshake(sec, request, response);
    }
}
