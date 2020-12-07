package org.carly.api.rest.response.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.core.ordermanagement.model.cart.PartType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPartsResponse {
    private String supplierId;
    private String supplierName;
    private Integer totalFactoryQuantity;
    private BigDecimal totalFactoryAmount;
    private Map<PartType, List<PartResponse>> parts;
}
