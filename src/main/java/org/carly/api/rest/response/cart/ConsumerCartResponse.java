package org.carly.api.rest.response.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerCartResponse {
    private String consumerId;
    private String consumerName;
    private Integer totalQuantity;
    private BigDecimal totalAmount;
    private List<SupplierPartsResponse> factoriesParts;
}
