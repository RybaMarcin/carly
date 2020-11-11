package org.carly.api.rest.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CarSearchCriteriaRequest {
    private List<String> nameToSearch;
    private List<String> carCodes;
    private List<String> brandNames;
    private List<String> models;

    public CarSearchCriteriaRequest() {
    }

    public static CarSearchCriteriaRequest ofNull() {
        return new CarSearchCriteriaRequest();
    }
}
