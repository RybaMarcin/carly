package org.carly.user_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.repository.VerificationTokenRepository;
import org.carly.user_management.security.token.VerificationToken;
import org.springframework.stereotype.Service;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service
public class TokenService {

    private final VerificationTokenRepository tokenRepository;

    public TokenService(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    VerificationToken getVerificationToken(String token) {
       return tokenRepository.findByToken(token).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}