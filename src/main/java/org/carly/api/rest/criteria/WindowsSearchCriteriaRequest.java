package org.carly.api.rest.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WindowsSearchCriteriaRequest extends PartSearchCriteriaRequest {

    private List<String> windowsCodes;
    private List<String> brandNames;

    public WindowsSearchCriteriaRequest() {
    }

    public static WindowsSearchCriteriaRequest ofNull() {
        return new WindowsSearchCriteriaRequest();
    }
}
