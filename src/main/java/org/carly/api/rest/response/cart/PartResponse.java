package org.carly.api.rest.response.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartResponse {
    private String partId;
    private String partName;
    private int quantity;
    private BigDecimal amountPerItem;
}
