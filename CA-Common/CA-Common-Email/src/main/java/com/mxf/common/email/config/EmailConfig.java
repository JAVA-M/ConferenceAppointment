package com.mxf.common.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author manxingfu
 * @date 2023/5/22 17:24
 * @desc
 */
//@Configuration
public class EmailConfig {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private boolean startlls_required;

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", String.valueOf(this.auth));
        properties.setProperty("mail.smtp.starttls.enable", String.valueOf(this.starttls));
        properties.setProperty("mail.smtp.starttls.required", String.valueOf(this.startlls_required));
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

    public String getUsername() {
        return username;
    }
}
