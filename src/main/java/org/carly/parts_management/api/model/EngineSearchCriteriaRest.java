package org.carly.parts_management.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineSearchCriteriaRest {

    private List<String> names;
    private List<String> engineCodes;
    private List<String> brandNames;


    public static EngineSearchCriteriaRest ofNull() {
        return new EngineSearchCriteriaRest();
    }

}
