package org.carly.user_management.api.model;

import lombok.AllArgsConstructor;
import org.carly.user_management.security.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
public class CarlyUserRest {
    private String id;
    private String name;
    private String email;
    private UserRole role;

}
