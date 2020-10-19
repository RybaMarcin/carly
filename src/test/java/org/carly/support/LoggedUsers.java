package org.carly.support;

import org.carly.core.shared.security.model.CarlyGrantedAuthority;
import org.carly.core.shared.security.model.LoggedUser;
import org.carly.core.shared.security.model.LoggedUserBuilder;
import org.carly.core.shared.security.model.UserRole;

import java.util.List;

public class LoggedUsers {

    public static final String LOGGED_USER_ID_1 = "5da0715c1c9d440000f105fd";
    public static final String LOGGED_USER_EMAIL_1 = "johns.mail@killme.com";
    public static final String LOGGED_USER_NAME_1 = "John";
    public static final List<CarlyGrantedAuthority> LOGGED_USER_AUTHORITIES_1 =
            List.of(new CarlyGrantedAuthority(UserRole.CARLY_CUSTOMER), new CarlyGrantedAuthority(UserRole.CHANGE_PASSWORD_PRIVILEGE));
    public static final boolean LOGGED_USER_ENABLE_1 = true;

    public static LoggedUser aLoggedUser1() {
        return new LoggedUserBuilder()
                .withId(LOGGED_USER_ID_1)
                .withEmail(LOGGED_USER_EMAIL_1)
                .withName(LOGGED_USER_NAME_1)
                .withAuthorities(LOGGED_USER_AUTHORITIES_1)
                .withEnabled(LOGGED_USER_ENABLE_1)
                .build();
    }
}
