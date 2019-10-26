package org.carly.parts_management.api.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WindowsSearchCriteriaRest extends PartSearchCriteriaRest{

    private List<String> windowsCodes;
    private List<String> brandNames;


    public WindowsSearchCriteriaRest() {
    }

    public static WindowsSearchCriteriaRest ofNull() {
        return new WindowsSearchCriteriaRest();
    }

}
