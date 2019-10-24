package org.carly.support;

import org.carly.user_management.api.model.LoginRest;

import static org.carly.support.Users.EMAIL_1;

public class LoginRests {

    public static final String LOGIN_EMAIL_ = EMAIL_1;
    public static final String PASSWORD_1 = "johnpassword123";

    public static LoginRest aLoginRest1() {
        return new LoginRest(EMAIL_1, PASSWORD_1);
    }
}
