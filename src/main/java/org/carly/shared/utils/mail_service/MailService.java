package org.carly.shared.utils.mail_service;

import org.carly.user_management.core.config.LoggedUserProvider;
import org.carly.user_management.core.model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final LoggedUserProvider loggedUserProvider;

    public MailService(JavaMailSender mailSender,
                       LoggedUserProvider loggedUserProvider) {
        this.mailSender = mailSender;
        this.loggedUserProvider = loggedUserProvider;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void send(SimpleMailMessage simpleMailMessage) {
        mailSender.send(simpleMailMessage);
    }

    public SimpleMailMessage constructSimpleMessage(String subject, String body, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(loggedUserProvider.provideCurrent().getEmail());
        return email;
    }
}