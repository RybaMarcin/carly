package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;
import org.carly.core.companymanagement.model.CompanyMatchStatus;

@Getter
@Setter
public class CompanyFactoryMatchRequest {

    private String companyMatchId;
    private CompanyMatchStatus statusResponse;
}
