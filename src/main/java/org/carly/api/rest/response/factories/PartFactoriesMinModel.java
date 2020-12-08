package org.carly.api.rest.response.factories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.core.ordermanagement.model.cart.PartType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartFactoriesMinModel {
    private String partId;
    private String name;
    private PartType partType;
}
