package org.carly.core.security.model;

import org.springframework.security.core.GrantedAuthority;

public class CarlyGrantedAuthority implements GrantedAuthority {

    private final UserRole userRole;

    public CarlyGrantedAuthority(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getAuthority() {
        return userRole.name();
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return userRole.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarlyGrantedAuthority that = (CarlyGrantedAuthority) o;
        return userRole == that.userRole;
    }

    @Override
    public int hashCode() {
        return userRole != null ? userRole.hashCode() : 0;
    }

    public static CarlyGrantedAuthority of(String userRole) {
        return new CarlyGrantedAuthority(UserRole.valueOf(userRole));
    }
}
