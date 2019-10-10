package org.carly.user_management.api.model;

import lombok.Getter;
import org.carly.shared.security.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginRest {
    @ValidEmail
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
