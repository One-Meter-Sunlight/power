package com.imooc.power.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

/**
 * 邮件发送工具类
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/8/3 12:57
 */
public class MailUtil {

    @Value("spring.mail.username")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    private void sendTextMail(String subject, String to, String cc, String bcc, String content) {
        // 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setBcc(bcc);
        message.setSentDate(new Date());
        message.setText(content);
        javaMailSender.send(message);
    }


}
