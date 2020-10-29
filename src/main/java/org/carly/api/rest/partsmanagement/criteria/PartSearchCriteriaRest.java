package org.carly.api.rest.partsmanagement.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PartSearchCriteriaRest {

    private List<String> nameToSearch;
}
