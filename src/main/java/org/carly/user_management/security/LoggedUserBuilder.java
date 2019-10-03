package org.carly.user_management.security;

import java.util.List;

public class LoggedUserBuilder {
    private String id;
    private String email;
    private String name;
    private List<CarlyGrantedAuthority> authorities;

    public LoggedUserBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public LoggedUserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public LoggedUserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LoggedUserBuilder withAuthorities(List<CarlyGrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public LoggedUser build() {
        return new LoggedUser(id, email, name, authorities);
    }

}
