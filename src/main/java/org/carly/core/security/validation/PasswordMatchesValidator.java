package org.carly.core.security.validation;

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
        SignupRequest user = (SignupRequest) obj;
        return user.getPassword().equals(user.getRePassword());
    }
}
