package org.carly.shared.security.test;

import org.carly.shared.security.model.UserRole;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithLoggedUserSecurityContextFactory.class)
public @interface WithLoggedUser {

    String id() default "";

    String name() default "";

    String email() default "";

    UserRole role() default UserRole.CARLY_CUSTOMER;
}
