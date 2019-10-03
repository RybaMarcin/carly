package org.carly.user_management.core.service;

import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.carly.user_management.core.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class VerificationService {

    private final ApplicationEventPublisher eventPublisher;

    public VerificationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public OnRegistrationCompleteEvent publish(User user, WebRequest request) {
        OnRegistrationCompleteEvent onRegistrationCompleteEvent = null;
        try {
            String appUrl = request.getContextPath();
            onRegistrationCompleteEvent = new OnRegistrationCompleteEvent(appUrl, request.getLocale(), user);
            eventPublisher.publishEvent(onRegistrationCompleteEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return onRegistrationCompleteEvent;
    }
}