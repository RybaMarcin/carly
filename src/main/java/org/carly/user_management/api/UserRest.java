package org.carly.user_management.api;

import lombok.Getter;
import lombok.Setter;
import org.carly.user_management.utils.PasswordMatcher;
import org.carly.user_management.utils.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@PasswordMatcher
public class UserRest {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String lastName;
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String matchingPassword;

}
