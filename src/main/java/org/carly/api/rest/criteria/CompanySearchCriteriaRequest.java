package org.carly.api.rest.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanySearchCriteriaRequest {

    private List<String> namesToSearch;

    public CompanySearchCriteriaRequest(List<String> namesToSearch) {
        this.namesToSearch = namesToSearch;
    }
}
