package org.carly.user_management.api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRest {
    private String email;
    private String password;
}
