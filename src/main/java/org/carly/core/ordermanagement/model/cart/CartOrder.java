package org.carly.core.ordermanagement.model.cart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartOrder {
    private String consumerId;
    private String consumerName;
    private int totalQuantity;
    private BigDecimal totalAmount;
    private List<SpecificFactory> factoryParts;

    public CartOrder(String consumerId, String consumerName, List<SpecificFactory> factoryParts) {
        this.consumerId = consumerId;
        this.consumerName = consumerName;
        this.factoryParts = factoryParts;
    }
}
