package org.carly.api.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.security.validation.PasswordMatcher;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@PasswordMatcher
@Getter
@Setter
public class SignupCompanyRequest {

    @NotBlank
    @Size(max = 40)
    private String companyName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String rePassword;
    @NotBlank
    private String phone;

    private AddressRequest addressRequest;
}
