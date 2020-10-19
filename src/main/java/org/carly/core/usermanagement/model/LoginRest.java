package org.carly.core.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.carly.core.shared.security.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class LoginRest {
    @ValidEmail
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
