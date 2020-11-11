package org.carly.core.usermanagement.service;

import org.carly.core.shared.utils.mail_service.MailService;
import org.carly.core.usermanagement.model.OnRegistrationCompleteEvent;
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