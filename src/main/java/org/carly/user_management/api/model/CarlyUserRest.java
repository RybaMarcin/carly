package org.carly.user_management.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.carly.shared.security.model.UserRole;

@AllArgsConstructor
@Getter
public class CarlyUserRest {
    private String id;
    private String name;
    private String email;
    private UserRole role;
}
