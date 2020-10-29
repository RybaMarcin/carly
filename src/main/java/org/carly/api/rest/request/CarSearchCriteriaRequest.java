package org.carly.api.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
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
