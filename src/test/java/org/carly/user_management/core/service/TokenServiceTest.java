package org.carly.user_management.core.service;

import org.carly.shared.utils.time.TimeService;
import org.carly.user_management.core.model.VerificationToken;
import org.carly.user_management.core.repository.VerificationTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.carly.support.Users.USER_ID_1;
import static org.carly.support.Users.aUser1;
import static org.carly.support.VerificationTokens.TOKEN_1;
import static org.carly.support.VerificationTokens.aVerificationTokenIsExpire;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @Mock
    VerificationTokenRepository tokenRepository;
    @Mock
    TimeService timeService;
    @InjectMocks
    TokenService tokenService;

    @Test
    void createVerificationToken() {
        //given
        final LocalDateTime tokenExpire = LocalDateTime.of(2019, 10, 11, 21, 38, 10, 1);
        //and
        when(timeService.getLocalDateTime()).thenReturn(tokenExpire);
        //when
        String returnedToken = tokenService.createVerificationToken(aUser1());
        //then
        verify(tokenRepository, times(1)).save(any());
        assertThat(returnedToken).isNotNull();
    }

    @Test
    void getVerificationToken() {
        //given
        when(tokenRepository.findByToken(TOKEN_1)).thenReturn(Optional.of(aVerificationTokenIsExpire()));
        //when
        VerificationToken verificationToken = tokenService.getVerificationToken(TOKEN_1);
        //then
        assertThat(verificationToken).isNotNull();
        assertThat(verificationToken.getUser().getId()).isEqualTo(USER_ID_1);
    }

    @Test
    void removeToken(){
        //given
        VerificationToken verificationToken = aVerificationTokenIsExpire();
        //when
        tokenService.removeToken(verificationToken);
        //then
        verify(tokenRepository, times(1)).delete(verificationToken);
    }
}