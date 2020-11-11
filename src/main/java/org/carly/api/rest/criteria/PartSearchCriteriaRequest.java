package org.carly.api.rest.criteria;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class PartSearchCriteriaRequest {

    private List<String> nameToSearch;
}
