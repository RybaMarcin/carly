package org.carly.api.rest.partsmanagement.criteria;

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
