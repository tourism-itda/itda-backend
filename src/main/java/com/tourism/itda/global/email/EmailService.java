package com.tourism.itda.global.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("itda_official24@naver.com");
        message.setTo(to);
        message.setSubject("[잇다] 비밀번호 재설정 인증코드");
        message.setText("인증코드: " + code + "\n\n5분 내로 입력해주세요.");
        mailSender.send(message);
    }
}
