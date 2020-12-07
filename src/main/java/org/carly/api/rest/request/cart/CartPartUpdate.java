package org.carly.api.rest.request.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.core.ordermanagement.model.cart.PartType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartPartUpdate {
    private String consumerId;
    private String supplierId;
    private String partId;
    private PartType partType;
}
