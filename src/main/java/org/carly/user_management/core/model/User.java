package org.carly.user_management.core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;
}
