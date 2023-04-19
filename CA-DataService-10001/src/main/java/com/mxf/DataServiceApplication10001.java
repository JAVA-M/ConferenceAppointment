package com.mxf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author manxingfu
 * @date 2022/12/13
 */

@EnableTransactionManagement
@SpringBootApplication
// @EnableDubbo
public class DataServiceApplication10001 {
    public static void main(String[] args) {
        SpringApplication.run(DataServiceApplication10001.class);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ数据服务模块启动成功!ლ(´ڡ`ლ)ﾞ\n" +
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
