package pl.tutors.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.tutors.domain.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {


//    private final JavaMailSender javaMailSender;

    public void sendPasswordResetEmail(User user) {
        String content = String.format("http://localhost:3000/password-change?token=%s&email=%s", user.getPasswordResetToken().toString(), user.getEmail());

        sendMessage(user.getEmail(), "Password reset", content);

    }

    private void sendMessage(String to, String subject, String content) {
//        try {
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//
//            final MimeMessageHelper message;
//
//            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            message.setSubject(subject);
//            message.setFrom("ochrona@danych.pl");
//            message.setTo(to);
//            message.setText(content, false);
//
//            log.info("sending email: {}", content);
//
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException | MailException e) {
//            log.error("Failed to send email: ");
//            e.printStackTrace();
//        }
        log.info("sending email: {}", content);
    }

    public void notifyAboutLogin(User user, String usedIp) {
        sendMessage(user.getEmail(), "Logged in", "User logged in with ip " + usedIp);
    }
}
