package org.carly.core.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class LoginRest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
