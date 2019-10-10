package org.carly.user_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.shared.utils.mail_service.MailService;
import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.carly.user_management.core.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MessageSource messageSource;
    private final TokenService tokenService;
    private final MailService mailService;

    public RegistrationListener(MessageSource messageSource,
                                TokenService tokenService,
                                MailService mailService) {
        this.messageSource = messageSource;
        this.tokenService = tokenService;
        this.mailService = mailService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = tokenService.createVerificationToken(user);
        log.info("token: {}", token);

        String confirmUrl = event.getAppUrl();
//        String message = messageSource.getMessage("message.regSucc", null, event.getLocale());
        String text = "http://localhost:8080/user/registrationConfirm.html?token=" + token;

        mailService.sendSimpleEmail(user.getEmail(), "Registration Confirmation", text);
        log.info("Mail was sent");
    }
}