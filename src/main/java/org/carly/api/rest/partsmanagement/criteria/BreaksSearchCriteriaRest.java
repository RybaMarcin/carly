package org.carly.api.rest.partsmanagement.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BreaksSearchCriteriaRest extends PartSearchCriteriaRest {

    private List<String> engineCodes;
    private List<String> brandNames;



    public static BreaksSearchCriteriaRest ofNull() {
        return new BreaksSearchCriteriaRest();
    }

}
