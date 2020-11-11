package org.carly.core.security.validation;

import org.carly.api.rest.request.SignupCompanyRequest;
import org.carly.api.rest.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatcher, Object> {

    @Override
    public void initialize(PasswordMatcher constraint) {
        //init
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof SignupRequest) {
            SignupRequest user = (SignupRequest) obj;
            return user.getPassword().equals(user.getRePassword());

        } else if (obj instanceof SignupCompanyRequest) {
            SignupCompanyRequest companyRequest = (SignupCompanyRequest) obj;
            return companyRequest.getPassword().equals(companyRequest.getRePassword());
        }
        return false;
    }
}
