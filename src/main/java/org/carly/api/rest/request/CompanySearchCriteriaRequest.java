package org.carly.api.rest.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CompanySearchCriteriaRequest {

    private List<String> namesToSearch;

    public CompanySearchCriteriaRequest(List<String> namesToSearch) {
        this.namesToSearch = namesToSearch;
    }
}
