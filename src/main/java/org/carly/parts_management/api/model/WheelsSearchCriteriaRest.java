package org.carly.parts_management.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WheelsSearchCriteriaRest {

    private List<String> names;
    private List<String> wheelsCodes;
    private List<String> brandNames;

    public WheelsSearchCriteriaRest() {
    }

    public static WheelsSearchCriteriaRest ofNull() {
        return new WheelsSearchCriteriaRest();
    }

}
