package org.carly.company_management.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanySearchCriteriaRest {

    private List<String> nameToSearch;


    public static CompanySearchCriteriaRest ofNull() {
        return new CompanySearchCriteriaRest();
    }

}
