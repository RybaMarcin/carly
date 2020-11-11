package org.carly.api.rest.criteria;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EngineSearchCriteriaRequest extends PartSearchCriteriaRequest {

    private List<String> engineCodes;
    private List<String> brandNames;


    public static EngineSearchCriteriaRequest ofNull() {
        return new EngineSearchCriteriaRequest();
    }

}
