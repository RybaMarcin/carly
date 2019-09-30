package org.carly.shared.sercurity.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {
    private Long id;
    private String name;
    private List<UserRole> authorities;

    public User(Long id, String name, List<UserRole> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    public boolean hasRole(UserRole userRole) {
        return authorities.stream().anyMatch(group -> group == userRole);
    }
}