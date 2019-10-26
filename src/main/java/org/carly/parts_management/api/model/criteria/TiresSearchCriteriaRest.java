package org.carly.parts_management.api.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TiresSearchCriteriaRest extends PartSearchCriteriaRest{

    private List<String> tiresCodes;
    private List<String> brandNames;

    public TiresSearchCriteriaRest() {
    }

    public static TiresSearchCriteriaRest ofNull() {
        return new TiresSearchCriteriaRest();
    }

}
