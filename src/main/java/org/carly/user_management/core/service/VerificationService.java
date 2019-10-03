package org.carly.user_management.core.service;

import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.repository.VerificationTokenRepository;
import org.carly.user_management.security.token.VerificationToken;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class VerificationService {

    private final ApplicationEventPublisher eventPublisher;
    private final VerificationTokenRepository tokenRepository;

    public VerificationService(ApplicationEventPublisher eventPublisher, VerificationTokenRepository tokenRepository) {
        this.eventPublisher = eventPublisher;
        this.tokenRepository = tokenRepository;
    }

    public void publish(User user, WebRequest request) {
        OnRegistrationCompleteEvent onRegistrationCompleteEvent;
        try {
            String appUrl = request.getContextPath();
            onRegistrationCompleteEvent = new OnRegistrationCompleteEvent(appUrl, request.getLocale(), user);
            eventPublisher.publishEvent(onRegistrationCompleteEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}