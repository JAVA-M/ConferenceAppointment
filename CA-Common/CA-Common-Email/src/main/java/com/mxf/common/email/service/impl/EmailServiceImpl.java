package com.mxf.common.email.service.impl;

import com.mxf.common.email.domain.Email;
import com.mxf.common.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @author manxingfu
 * @date 2023/5/22 17:31
 * @desc
 */
@Service
public class EmailServiceImpl implements EmailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void send(Email mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setFrom(fromEmail); // username 即 发送者的email账号

        for (String address : mail.getAddresses()) {
            message.setTo(address);
            try {
                mailSender.send(message);
                logger.info("完成向{}的邮件发送", address);
            } catch (MailException e) {
                logger.error("向{}进行邮件发送失败，错误信息{}", address, e.getMessage());
            }
        }
    }
}
