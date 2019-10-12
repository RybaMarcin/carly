package org.carly.user_management.api.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.shared.security.validation.PasswordMatcher;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatcher
@Getter
@Setter
public class UserRest {
    private ObjectId id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
//    @ValidEmail
    private String email;
    @NotNull
    private String gender;
    @NotEmpty
    private String password;
    @NotEmpty
    private String matchingPassword;
}
