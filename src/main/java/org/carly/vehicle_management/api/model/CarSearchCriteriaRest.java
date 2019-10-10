package org.carly.vehicle_management.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarSearchCriteriaRest {
    private List<String> names;
    private List<String> carCodes;
    private List<String> brandNames;
    private List<String> models;

    public CarSearchCriteriaRest() {
    }

    public static CarSearchCriteriaRest ofNull() {
        return new CarSearchCriteriaRest();
    }
}
