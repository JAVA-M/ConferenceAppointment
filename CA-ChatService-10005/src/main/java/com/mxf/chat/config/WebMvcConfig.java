package com.mxf.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author manxingfu
 * @date 2023/5/24 10:59
 * @desc
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 映射的路径
                //.allowedOrigins("*") // 允许跨域的域名，* 表示允许任意域名
                .allowedOriginPatterns("*")
                .allowedMethods("*") // 允许跨域的请求方法，* 表示允许任意方法
                .allowedHeaders("*") // 允许跨域的请求头，* 表示允许任意请求头
                .allowCredentials(true); // 是否允许发送 Cookie
    }
}
