package org.carly.core.shared.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoggedUser implements UserDetails {
    private String id;
    private String companyId;
    private String email;
    private String name;
    private List<CarlyGrantedAuthority> authorities;
    private boolean userEnable;

    public LoggedUser(String id, String companyId, String email, String name, List<CarlyGrantedAuthority> authorities, boolean userEnable) {
        this.id = id;
        this.companyId = companyId;
        this.email = email;
        this.name = name;
        this.authorities = authorities;
        this.userEnable = userEnable;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyId() { return companyId; }

    public String getEmail() {
        return email;
    }

    private boolean isUserEnable() {
        return userEnable;
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
        return isUserEnable();
    }

    public List<UserRole> getRoles() {
        return authorities.stream().map(CarlyGrantedAuthority::getUserRole)
                .collect(Collectors.toList());
    }
}