package org.carly.core.usermanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.usermanagement.model.User;
import org.carly.core.usermanagement.model.VerificationToken;
import org.carly.core.usermanagement.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public String createVerificationToken(User user) {
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