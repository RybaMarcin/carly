package org.carly.api.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.companymanagement.model.CompanyMatchStatus;

@Getter
@Setter
@AllArgsConstructor
public class CompanyMatchResponse {

    private String companyName;
    private String factoryName;
    private CompanyMatchStatus status;
}
