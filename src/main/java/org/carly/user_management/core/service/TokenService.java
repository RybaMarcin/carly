package org.carly.user_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.utils.time.TimeService;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.repository.VerificationTokenRepository;
import org.carly.user_management.core.model.VerificationToken;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service
public class TokenService {

    private final VerificationTokenRepository tokenRepository;
    private final TimeService timeService;

    public TokenService(VerificationTokenRepository tokenRepository,
                        TimeService timeService) {
        this.tokenRepository = tokenRepository;
        this.timeService = timeService;
    }

    String createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken myToken = new VerificationToken(user, token, timeService.getLocalDateTime().plusHours(24));
        tokenRepository.save(myToken);
        return token;
    }

    VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token).orElse(null);
    }

    void removeToken(VerificationToken token) {
        if (token != null) {
            tokenRepository.delete(token);
            log.info("Token was deleted");
            return;
        }
        log.info("Token don't exists");
    }
}