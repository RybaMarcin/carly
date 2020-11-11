package org.carly.api.rest.criteria;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EquipmentSearchCriteriaRequest extends PartSearchCriteriaRequest {

    private List<String> equipmentCodes;
    private List<String> brandNames;

    public EquipmentSearchCriteriaRequest() {
    }

    public static EquipmentSearchCriteriaRequest ofNull() {
        return new EquipmentSearchCriteriaRequest();
    }
}
