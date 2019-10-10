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
        String token = generateToken();
        VerificationToken myToken = new VerificationToken(user, token, timeService.getLocalDateTime().plusHours(24));
        tokenRepository.save(myToken);
        return token;
    }

    private static String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[20];
        secureRandom.nextBytes(bytes);
        return Arrays.toString(bytes);
    }

    VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}