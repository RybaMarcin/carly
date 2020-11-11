package org.carly.support;

import org.carly.core.usermanagement.model.User;
import org.carly.core.usermanagement.model.VerificationToken;

import java.time.LocalDateTime;

import static org.carly.support.Users.aUser1;

public class VerificationTokens {

    public static final String TOKEN_1 = "a407ba64-cb24-4d69-8c1a-f3c95cd7d82f";
    public static final User USER_1 = aUser1();
    public static final LocalDateTime EXPIRY_DATE_1 = LocalDateTime.of(2019, 10, 12, 22, 35, 10, 1);

    public static final String TOKEN_2 = "446c1638-5d8e-4172-ac01-fbb12c3afe3e";

    public static VerificationToken aVerificationTokenIsExpire() {
        return new VerificationToken(USER_1, TOKEN_1, EXPIRY_DATE_1);
    }

    public static VerificationToken aVerificationTokenNonExpire(){
        return new VerificationToken(USER_1, TOKEN_1, LocalDateTime.now().minusHours(23));
    }
}