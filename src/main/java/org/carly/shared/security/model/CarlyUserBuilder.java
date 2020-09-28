package org.carly.shared.security.model;

import org.carly.user_management.api.model.CarlyUserRest;

public class CarlyUserBuilder {
    private String id;
    private String companyId;
    private String name;
    private String email;
    private UserRole role;

    public CarlyUserBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CarlyUserBuilder withCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public CarlyUserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CarlyUserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CarlyUserBuilder withRole(UserRole role) {
        this.role = role;
        return this;
    }

    public CarlyUserRest build() {
        return new CarlyUserRest(id, companyId, name, email, role);
    }
}
