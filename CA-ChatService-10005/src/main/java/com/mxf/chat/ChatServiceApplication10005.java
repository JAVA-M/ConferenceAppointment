package com.mxf.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * @author manxingfu
 * @date 2023/5/23 18:43
 * @desc
 */
//@EnableWebSocketMessageBroker
@SpringBootApplication
public class ChatServiceApplication10005 {
    public static void main(String[] args) {
        new SpringApplication().run(ChatServiceApplication10005.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ聊天服务模块启动成功!ლ(´ڡ`ლ)ﾞ\n" +
                        "  ,ad8888ba,          db         \n" +
                        " d8\"'    `\"8b        d88b        \n" +
                        "d8'                 d8'`8b       \n" +
                        "88                 d8'  `8b      \n" +
                        "88                d8YaaaaY8b     \n" +
                        "Y8,              d8\"\"\"\"\"\"\"\"8b    \n" +
                        " Y8a.    .a8P   d8'         `8b   \n" +
                        "  `\"Y8888Y\"'  d8'            `8b "
        );
    }
}
