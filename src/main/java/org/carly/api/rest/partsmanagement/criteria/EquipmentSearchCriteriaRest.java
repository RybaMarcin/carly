package org.carly.api.rest.partsmanagement.criteria;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EquipmentSearchCriteriaRest extends PartSearchCriteriaRest {

    private List<String> equipmentCodes;
    private List<String> brandNames;

    public EquipmentSearchCriteriaRest() {
    }

    public static EquipmentSearchCriteriaRest ofNull() {
        return new EquipmentSearchCriteriaRest();
    }

}
