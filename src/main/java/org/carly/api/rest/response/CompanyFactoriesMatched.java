package org.carly.api.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CompanyFactoriesMatched {
    private List<CompanyMatchResponse> companyFactoriesResponse;
}
