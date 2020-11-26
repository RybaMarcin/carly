package org.carly.core.companymanagement.model;

import org.carly.core.shared.utils.criteria.CriteriaBuilder;
import org.carly.core.usermanagement.model.User;

public enum CompanyFilter {

    COMPANY_NAME(CriteriaBuilder.path(User.COMPANY_FIELD_NAME, Company.NAME)),
    COMPANY_ID(CriteriaBuilder.path(User.ID));
    private final String filter;

    CompanyFilter(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }
}
