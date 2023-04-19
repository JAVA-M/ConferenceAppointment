package com.mxf.file;

import com.mxf.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author manxingfu
 * @date 2023/3/15
 */
@EnableCustomSwagger2
@SpringBootApplication(scanBasePackages = "com.mxf")
public class FileServiceApplication10003 {
    public static void main(String[] args) {
        SpringApplication.run(FileServiceApplication10003.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ文件服务模块启动成功!ლ(´ڡ`ლ)ﾞ\n" +
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
