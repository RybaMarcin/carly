package org.carly.core.companymanagement.model;

import org.carly.core.security.model.CarlyGrantedAuthority;
import org.carly.core.shared.utils.criteria.CriteriaBuilder;
import org.carly.core.usermanagement.model.User;

public enum CompanyFilter {

    COMPANY_NAME(CriteriaBuilder.path(User.COMPANY_FIELD_NAME, Company.NAME)),
    COMPANY_ID(CriteriaBuilder.path(User.ID)),
    COMPANY_ROLE(CriteriaBuilder.path(User.ROLES, CarlyGrantedAuthority.USER_ROLE));
    private final String filter;

    CompanyFilter(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }
}
