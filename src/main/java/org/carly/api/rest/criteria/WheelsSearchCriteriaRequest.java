package org.carly.api.rest.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WheelsSearchCriteriaRequest extends PartSearchCriteriaRequest {

    private List<String> wheelsCodes;
    private List<String> brandNames;

    public WheelsSearchCriteriaRequest() {
    }

    public static WheelsSearchCriteriaRequest ofNull() {
        return new WheelsSearchCriteriaRequest();
    }

}
