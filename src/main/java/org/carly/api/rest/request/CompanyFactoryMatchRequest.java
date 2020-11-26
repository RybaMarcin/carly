package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;
import org.carly.core.companymanagement.model.CompanyMatchStatus;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CompanyFactoryMatchRequest {

    @NotNull
    private String companyMatchId;
    @NotNull
    private CompanyMatchStatus statusResponse;
}
