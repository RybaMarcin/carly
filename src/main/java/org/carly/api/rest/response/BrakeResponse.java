package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.BrakeType;

import java.math.BigDecimal;

@Getter
@Setter
public class BrakeResponse {
    private String id;
    private String name;
    private FactoryResponse brand;
    private String preview;
    private BrakeType brakeType;
    private BigDecimal price;
}
