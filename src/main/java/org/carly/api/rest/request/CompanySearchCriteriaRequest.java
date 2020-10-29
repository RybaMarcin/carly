package org.carly.api.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanySearchCriteriaRequest {

    private List<String> nameToSearch;

    public static CompanySearchCriteriaRequest ofNull() {
        return new CompanySearchCriteriaRequest();
    }
}
