package com.example.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailNotificationService {

    private final JavaMailSender javaMailSender;

    @Value("${notification.team-email}")
    private String toEmail;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailNotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void send(String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
            log.info("Email sent to {}: {}", toEmail, subject);
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage());
        }
    }


}
