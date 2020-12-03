package org.carly.api.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.core.ordermanagement.model.cart.PartType;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartToCartRequest {
    private String consumerId;
    private String consumerName;
    private String supplierId;
    private String supplierName;
    private String partId;
    private String partName;
    private PartType partType;
    private int quantity;
    private BigDecimal amount;
}
