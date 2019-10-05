package org.carly.user_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.carly.user_management.core.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MessageSource messageSource;
    private final TokenService tokenService;
    private final JavaMailSender mailSender;

    public RegistrationListener(MessageSource messageSource,
                                TokenService tokenService,
                                JavaMailSender mailSender) {
        this.messageSource = messageSource;
        this.tokenService = tokenService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = tokenService.createVerificationToken(user);
        log.info("token: {}", token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
        String message = messageSource.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " rn" + "http://localhost:8080" + confirmUrl);

        mailSender.send(email);
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}