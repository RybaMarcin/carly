package org.carly.core.companymanagement.mapper;

import org.carly.api.rest.response.CompanyMatchResponse;
import org.carly.core.companymanagement.model.CompanyMatch;
import org.springframework.stereotype.Component;

@Component
public class CompanyMatchMapper {

    public CompanyMatchResponse mapDomainToResponse(CompanyMatch domain) {
        String matchId = "";
        if (domain.getId() != null) {
            matchId = domain.getId().toHexString();
        }
        return new CompanyMatchResponse(
                matchId,
                domain.getCompanyName(),
                domain.getFactoryName(),
                domain.getStatus()
        );
    }
}
