package org.carly.api.rest;

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
