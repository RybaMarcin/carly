package org.carly.user_management.core.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.shared.security.model.CarlyGrantedAuthority;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

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
    private List<CarlyGrantedAuthority> role;
    private LocalDate createdAt;
    private boolean enabled;
}
