package org.carly.user_management.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.shared.security.validation.ValidEmail;

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
