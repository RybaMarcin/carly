package org.carly.core.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.carly.core.security.model.UserRole;

@AllArgsConstructor
@Getter
public class CarlyUserRest {
    private String id;
    private String companyId;
    private String name;
    private String email;
    private UserRole role;
}
