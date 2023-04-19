package com.mxf.service.utils;

import com.mxf.domain.vo.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @author manxingfu
 * @date 2023/2/6
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public boolean send(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setFrom(fromMail);
        // mailSender.send(message);
        // 批量发送
        // message.setTo(mail.getAddresses().toArray(new String[0]));
        // System.out.println(fromMail);
        // System.out.println(protocols);
        message.setTo(mail.getAddresses().get(0));
        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
