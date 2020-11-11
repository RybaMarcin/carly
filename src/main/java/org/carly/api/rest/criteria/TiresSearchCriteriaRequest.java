package org.carly.api.rest.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TiresSearchCriteriaRequest extends PartSearchCriteriaRequest {

    private List<String> tiresCodes;
    private List<String> brandNames;

    public TiresSearchCriteriaRequest() {
    }

    public static TiresSearchCriteriaRequest ofNull() {
        return new TiresSearchCriteriaRequest();
    }

}
