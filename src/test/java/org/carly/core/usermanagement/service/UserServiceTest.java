package org.carly.core.usermanagement.service;

import org.bson.types.ObjectId;
import org.carly.core.shared.security.exceptions.LoginOrPasswordException;
import org.carly.core.shared.security.model.CarlyGrantedAuthority;
import org.carly.core.shared.security.model.LoggedUser;
import org.carly.core.shared.security.model.UserRole;
import org.carly.core.shared.utils.mail_service.MailService;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.usermanagement.model.AddressRest;
import org.carly.core.usermanagement.model.CarlyUserRest;
import org.carly.api.rest.auth.request.LoginRequest;
import org.carly.core.usermanagement.model.UserRest;
import org.carly.core.usermanagement.mapper.UserMapper;
import org.carly.core.usermanagement.model.User;
import org.carly.core.usermanagement.model.VerificationToken;
import org.carly.core.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.carly.support.AddressesModel.aAddress1;
import static org.carly.support.AddressesModel.aAddressRest1;
import static org.carly.support.LoggedUsers.aLoggedUser1;
import static org.carly.support.LoginRests.aLoginRest1;
import static org.carly.support.Users.*;
import static org.carly.support.Users.aUserWithAddress1;
import static org.carly.support.VerificationTokens.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    TokenService tokenService;
    @Mock
    MessageSource messageSource;
    @Mock
    UserMapper userMapper;
    @Mock
    TimeService timeService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    MailService mailService;
    @Captor
    ArgumentCaptor<User> userArgumentCaptor;
    @Captor
    ArgumentCaptor<String> passwordArgumentCaptor;
    @Captor
    ArgumentCaptor<AddressRest> addressArgumentCaptor;
    @InjectMocks
    UserService userService;

//    @Test
//    void createUser() {
//        //given
//        UserRest userRest = aUserRest1();
//        User user = aUser1();
//        WebRequest webRequest = mock(WebRequest.class);
//        //and
//        when(userRepository.findByEmail(userRest.getEmail())).thenReturn(Optional.ofNullable(null));
//        when(userMapper.simplifyDomainObject(userRest)).thenReturn(user);
//        when(userRepository.save(user)).thenReturn(user);
//        //when
//        User returnUser = userService.createUser(userRest, webRequest);
//        //then
//        assertThat(returnUser).isNotNull();
//        assertThat(returnUser.getEmail()).isEqualTo(userRest.getEmail());
//        verify(userRepository, times(1)).save(user);
//    }

    @Test
    void confirmRegistrationShouldReturn_TokenIsExpire() {
        //given
        WebRequest webRequest = mock(WebRequest.class);
        //and
        when(tokenService.getVerificationToken(TOKEN_1)).thenReturn(aVerificationTokenIsExpire());
        when(timeService.getLocalDateTime()).thenReturn(LocalDateTime.now());
        when(messageSource.getMessage(any(), any(), any()))
                .thenReturn("Token is expire!");
        //when
        String returnValue = userService.confirmRegistration(webRequest, TOKEN_1);
        assertThat(returnValue).isEqualTo("Token is expire!");
    }

    @Test
    void confirmRegistrationShouldReturn_Completed() {
        //given
        WebRequest webRequest = mock(WebRequest.class);
        String token = TOKEN_1;
        //and
        when(tokenService.getVerificationToken(token)).thenReturn(aVerificationTokenNonExpire());
        when(timeService.getLocalDateTime()).thenReturn(LocalDateTime.now());
        when(messageSource.getMessage(any(), any(), any()))
                .thenReturn("Registration complete!");
        //when
        String returnValue = userService.confirmRegistration(webRequest, token);
        assertThat(returnValue).isEqualTo("Registration complete!");
    }

    @Test
    void confirmRegistrationShouldReturn_TokenHasBeenUsedOnce() {
        //given
        WebRequest webRequest = mock(WebRequest.class);
        String token = TOKEN_1;
        //and
        when(tokenService.getVerificationToken(token)).thenReturn(null);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("Token has been already use!");
        //when
        String returnValue = userService.confirmRegistration(webRequest, token);
        //then
        assertThat(returnValue).isEqualTo("Token has been already use!");
    }

    @Test
    void login_shouldSuccessfullyLogIn() {
        //given
        LoginRequest userRest = aLoginRest1();
        User user = aUser1();
        //and
        when(userRepository.findByEmail(userRest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userRest.getPassword(), user.getPassword())).thenReturn(true);
        //when
        CarlyUserRest login = null;
        //then
        assertThat(login).isNotNull();
        assertThat(login.getRole()).isEqualTo(UserRole.CARLY_CUSTOMER);
        assertThat(login.getId()).isEqualTo(user.getId().toHexString());
    }

//    @Test
//    void login_shouldThrowPasswordException() {
//        //given
//        LoginRequest userRest = aLoginRest1();
//        User user = aUser1();
//        //and
//        when(userRepository.findByEmail(userRest.getEmail())).thenReturn(Optional.of(user));
//        doThrow(new LoginOrPasswordException("Password or Email was incorrect"))
//                .when(passwordEncoder).matches(anyString(), any());
//        //when
//        Executable executable = () -> userService.login(userRest, null);
//        assertThrows(LoginOrPasswordException.class, executable);
//    }

    @Test
    void resetUserPassword() {
        //given
        HttpServletRequest request = mock(HttpServletRequest.class);
        String email = EMAIL_1;
        User user = aUser1();
        //and
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(tokenService.createVerificationToken(user)).thenReturn(aVerificationTokenNonExpire().getToken());
        //when
        ResponseEntity<String> response = userService.resetUserPassword(request, email);
        //then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void changePassword() {
        //given
        User user = aUser1();
        WebRequest request = mock(WebRequest.class);
        VerificationToken verificationToken = aVerificationTokenNonExpire();
        //and
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(tokenService.getVerificationToken(TOKEN_1)).thenReturn(verificationToken);
        when(timeService.getLocalDateTime()).thenReturn(LocalDateTime.now());
        when(messageSource.getMessage(any(), any(), any())).thenReturn("Registration complete!");
        //when
        String response = userService.changePassword(user.getId().toHexString(), verificationToken.getToken(), request);
        //then
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo("Registration complete!");
    }

    @Test
    void saveNewPassword() {
        //given
        LoggedUser loggedUser = aLoggedUser1();
        User user = aUser1();
        String newPassword = "5da0715c1c9d440000f105fd";
        //and
        when(userRepository.findById(new ObjectId(loggedUser.getId())))
                .thenReturn(Optional.of(user));
        //when
        ResponseEntity response = userService.saveNewPassword(loggedUser, newPassword);
        verify(userRepository).save(userArgumentCaptor.capture());
        verify(passwordEncoder).encode(passwordArgumentCaptor.capture());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userArgumentCaptor.getValue().getId()).isEqualTo(user.getId());
        assertThat(passwordArgumentCaptor.getValue()).isEqualTo(newPassword);
        assertThat(userArgumentCaptor.getValue().getRoles().contains
                (CarlyGrantedAuthority.of(UserRole.CHANGE_PASSWORD_PRIVILEGE.name()))).isFalse();
    }

    @Test
    void shouldAddAddressForUserForTheFirstTime() {
        //given
        ObjectId userId = USER_ID_1;
        AddressRest addressRest = aAddressRest1();
        LocalDate createdAt = LocalDate.of(2019, 2, 12);
        //and
        when(userRepository.findById(userId)).thenReturn(Optional.of(aUser1()));
        when(userMapper.mapAddressToDomain(addressRest)).thenReturn(aAddress1());
        when(timeService.getLocalDate()).thenReturn(createdAt);
        //when
        ResponseEntity response = userService.addAddress(userId, addressRest);
        verify(userRepository).save(userArgumentCaptor.capture());
        //then
        verify(userMapper).mapAddressToDomain(addressArgumentCaptor.capture());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(addressArgumentCaptor.getValue().getCountry()).isEqualTo("USA");
        assertThat(userArgumentCaptor.getValue().getAddress().getCreatedAt()).isEqualTo(createdAt);
    }

    @Test
    void shouldAddNewAddressToUser() {
        //given
        ObjectId userId = USER_ID_1;
        AddressRest addressRest = aAddressRest1();
        User user = aUserWithAddress1();
        LocalDate createdAt = LocalDate.of(2019, 10, 22);
        //and
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(timeService.getLocalDate()).thenReturn(createdAt);
        when(userMapper.mapAddressToDomain(addressRest)).thenReturn(aAddress1());
        //when
        ResponseEntity response = userService.addAddress(userId, addressRest);
        verify(userRepository).save(userArgumentCaptor.capture());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userArgumentCaptor.getValue().getCreatedAt()).isEqualTo(CREATED_AT_1);
        assertThat(userArgumentCaptor.getValue().getAddressHistory().size()).isEqualTo(1);
        assertThat(userArgumentCaptor.getValue().getAddressHistory().get(0).getModifiedAt()).isEqualTo(createdAt);
    }
}