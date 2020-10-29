package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.core.security.model.CarlyGrantedAuthority;
import org.carly.core.security.model.LoggedUser;
import org.carly.core.security.model.UserRole;

import java.util.List;

public class LoggedUsers {

    public static final ObjectId LOGGED_USER_ID_1 = new ObjectId("5da0715c1c9d440000f105fd");
    public static final String LOGGED_USER_EMAIL_1 = "johns.mail@killme.com";
    public static final String LOGGED_USER_PASSWORD = "password";
    public static final String LOGGED_USER_FIRST_NAME_1 = "John";
    public static final String LOGGED_USER_LAST_NAME_1 = "Wick";
    public static final ObjectId LOGGED_USER_COMPANY_ID_1 = new ObjectId();
    public static final List<CarlyGrantedAuthority> LOGGED_USER_AUTHORITIES_1 =
            List.of(new CarlyGrantedAuthority(UserRole.CARLY_CUSTOMER), new CarlyGrantedAuthority(UserRole.CHANGE_PASSWORD_PRIVILEGE));
    public static final boolean LOGGED_USER_ENABLE_1 = true;

    public static LoggedUser aLoggedUser1() {
        return new LoggedUser(LOGGED_USER_ID_1, LOGGED_USER_COMPANY_ID_1, LOGGED_USER_FIRST_NAME_1, LOGGED_USER_LAST_NAME_1, LOGGED_USER_EMAIL_1, LOGGED_USER_PASSWORD, LOGGED_USER_AUTHORITIES_1, LOGGED_USER_ENABLE_1);
    }

}
