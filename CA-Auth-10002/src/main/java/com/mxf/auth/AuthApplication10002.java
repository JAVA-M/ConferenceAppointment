package com.mxf.auth;

import com.mxf.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCustomSwagger2
@EnableFeignClients(basePackages = "com.mxf")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }, scanBasePackages = "com.mxf")
public class AuthApplication10002 {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication10002.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ认证授权中心启动成功!ლ(´ڡ`ლ)ﾞ\n" +
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
