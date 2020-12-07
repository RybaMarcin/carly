package org.carly.api.rest.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartRequest {
    private String partId;
    private String partName;
    private int quantity;
    private BigDecimal amountPerItem;
}
