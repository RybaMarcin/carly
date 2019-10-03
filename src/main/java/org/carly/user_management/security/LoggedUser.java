package org.carly.user_management.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoggedUser implements UserDetails {
    private String id;
    private String email;
    private String name;
    private List<CarlyGrantedAuthority> authorities;

    public LoggedUser(String id, String email, String name, List<CarlyGrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return id;
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

    public List<UserRole> getRoles() {
        return authorities.stream().map(CarlyGrantedAuthority::getUserRole)
                .collect(Collectors.toList());
    }
}