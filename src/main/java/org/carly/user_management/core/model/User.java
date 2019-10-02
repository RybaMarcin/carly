package org.carly.user_management.core.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.user_management.security.UserRole;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "users")
public class User {
    private ObjectId id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;
    private UserRole role;
    private LocalDate createdAt;
}
