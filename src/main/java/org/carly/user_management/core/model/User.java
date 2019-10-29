package org.carly.user_management.core.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.shared.security.model.CarlyGrantedAuthority;
import org.carly.vehicle_management.core.model.Car;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
public class User {
    private ObjectId id;
    private List<CarlyGrantedAuthority> role;
    private String code;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private String password;
    private LocalDate createdAt;
    private boolean enabled;
}