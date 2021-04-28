package org.carly.api.rest.response.factories;

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
public class PartMinModel {
    private String partId;
    private String name;
    private BigDecimal price;
    private PartType partType;
}
