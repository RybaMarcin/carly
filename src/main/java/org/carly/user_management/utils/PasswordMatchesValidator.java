package org.carly.user_management.utils;

import org.carly.user_management.api.model.UserRest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatcher, Object> {

    @Override
    public void initialize(PasswordMatcher constraint) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserRest user = (UserRest) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
