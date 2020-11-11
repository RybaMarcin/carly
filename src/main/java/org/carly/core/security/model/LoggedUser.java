package org.carly.core.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.carly.core.usermanagement.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LoggedUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final ObjectId id;
    private String firstName;
    private String lastName;
    private String name;
    private final String email;
    private final Boolean enabled;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public LoggedUser(ObjectId id, String firstName,
                      String lastName, String email, String password,
                      Collection<? extends GrantedAuthority> authorities, Boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public LoggedUser(ObjectId id, String name, String email, String password,
                      Collection<? extends GrantedAuthority> authorities, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public static LoggedUser build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getUserRole().name()))
                .collect(Collectors.toList());

        if (user.getCustomer() != null) {

            return new LoggedUser(
                    user.getId(),
                    user.getCustomer().getFirstName(),
                    user.getCustomer().getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    authorities,
                    user.getEnabled());
        } else  {
            return new LoggedUser(
                    user.getId(),
                    user.getCompany().getName(),
                    user.getEmail(),
                    user.getPassword(),
                    authorities,
                    user.getEnabled());
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public ObjectId getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LoggedUser user = (LoggedUser) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
                "id=" + id +
                ", name=" + name +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }
}
