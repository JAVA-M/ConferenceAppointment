package com.mxf.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author manxingfu
 * @date 2023/2/17
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }, scanBasePackages = "com.mxf")
public class GatewayApplication10010 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication10010.class, args);
        System.out.println(
                 "(♥◠‿◠)ﾉﾞ网关服务模块启动成功!ლ(´ڡ`ლ)ﾞ\n" +
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
