package org.carly.core.companymanagement.mapper;

import org.carly.api.rest.response.CompanyMatchResponse;
import org.carly.core.companymanagement.model.CompanyMatch;
import org.springframework.stereotype.Component;

@Component
public class CompanyMatchMapper {

    public CompanyMatchResponse mapDomainToResponse(CompanyMatch domain) {
        String matchId = "";
        String factoryId = "";
        if (domain.getId() != null) {
            matchId = domain.getId().toHexString();
        }
        if (domain.getFactoryId() != null) {
            factoryId = domain.getFactoryId().toHexString();
        }
        return new CompanyMatchResponse(
                matchId,
                domain.getCompanyName(),
                factoryId,
                domain.getFactoryName(),
                domain.getStatus()
        );
    }
}
