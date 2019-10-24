package org.carly.user_management.core.service;

import org.carly.shared.utils.mail_service.MailService;
import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MailService mailService;

    public RegistrationListener(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        mailService.confirmRegistration(event);
    }
}