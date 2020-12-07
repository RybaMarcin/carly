package org.carly.core.ordermanagement.model.cart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SpecificFactory {
    private String factoryId;
    private String factoryName;
    private BigDecimal totalAmountOfFactory;
    private int totalQuantityOfFactory;
    private Map<PartType, List<SpecificPart>> parts;

    public SpecificFactory(String factoryId, String factoryName, Map<PartType, List<SpecificPart>> parts) {
        this.factoryId = factoryId;
        this.factoryName = factoryName;
        this.parts = parts;
    }
}
