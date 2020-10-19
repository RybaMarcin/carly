package org.carly.core.shared.security.test;

import org.carly.core.shared.security.model.CarlyGrantedAuthority;
import org.carly.core.shared.security.model.LoggedUser;
import org.carly.core.shared.security.model.LoggedUserBuilder;
import org.carly.core.shared.security.model.UserRole;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.List;

public class WithLoggedUserSecurityContextFactory implements WithSecurityContextFactory<WithLoggedUser> {

    @Override
    public SecurityContext createSecurityContext(WithLoggedUser annotation) {

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        LoggedUser userPrincipal = new LoggedUserBuilder()
                .withId(annotation.id())
                .withName(annotation.name())
                .withEmail(annotation.email())
                .withAuthorities(createRole(annotation.role()))
                .withEnabled(true)
                .build();
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken
                (userPrincipal, null, userPrincipal.getAuthorities());
        context.setAuthentication(authentication);
        return context;
    }

    private List<CarlyGrantedAuthority> createRole(UserRole role) {
        return List.of(new CarlyGrantedAuthority(role));
    }
}
