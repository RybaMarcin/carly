package org.carly.api.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.core.ordermanagement.model.cart.PartType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactoryRequest {
    private String factoryId;
    private PartType partType;
}
