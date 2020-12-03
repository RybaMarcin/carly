package org.carly.core.ordermanagement.model.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartOrder {
    private String consumerId;
    private String consumerName;
    private List<SpecificFactory> factoryParts;
}
