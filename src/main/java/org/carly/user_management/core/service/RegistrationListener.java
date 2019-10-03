package org.carly.user_management.core.service;

import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.carly.user_management.core.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MessageSource messageSource;
    private final VerificationService verificationService;
    private final JavaMailSender mailSender;

    public RegistrationListener(MessageSource messageSource,
                                VerificationService verificationService,
                                JavaMailSender mailSender) {
        this.messageSource = messageSource;
        this.verificationService = verificationService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationService.createVerificationToken(user, token);

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

}