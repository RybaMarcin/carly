package org.carly.api.rest.auth.request;

import lombok.Getter;
import lombok.Setter;
import org.carly.core.shared.security.validation.PasswordMatcher;

import javax.validation.constraints.*;

@PasswordMatcher
@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phone;

    @NotNull
    private String gender;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 40)
    private String rePassword;

}
