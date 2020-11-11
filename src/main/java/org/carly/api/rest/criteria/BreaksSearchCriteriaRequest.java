package org.carly.api.rest.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BreaksSearchCriteriaRequest extends PartSearchCriteriaRequest {

    private List<String> engineCodes;
    private List<String> brandNames;

    public static BreaksSearchCriteriaRequest ofNull() {
        return new BreaksSearchCriteriaRequest();
    }

}
