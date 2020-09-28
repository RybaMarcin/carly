package org.carly.shared.security.model;

import java.util.List;

public class LoggedUserBuilder {
    private String id;
    private String companyId;
    private String email;
    private String name;
    private List<CarlyGrantedAuthority> authorities;
    private boolean enable;

    public LoggedUserBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public LoggedUserBuilder withCompanyId(String companyId) {
        this.companyId = companyId;
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
    public LoggedUserBuilder withEnabled(boolean isEnabled){
        this.enable = isEnabled;
        return this;
    }

    public LoggedUser build() {
        return new LoggedUser(id, companyId, email, name, authorities, enable);
    }

}
