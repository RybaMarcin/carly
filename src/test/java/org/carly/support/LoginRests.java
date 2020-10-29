package org.carly.support;

import org.carly.api.rest.request.LoginRequest;

import static org.carly.support.Users.EMAIL_1;

public class LoginRests {

    public static final String LOGIN_EMAIL_ = EMAIL_1;
    public static final String PASSWORD_1 = "johnpassword123";

    public static LoginRequest aLoginRest1() {
        return new LoginRequest(EMAIL_1, PASSWORD_1);
    }
}
