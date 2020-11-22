package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.api.rest.response.FactoryResponse;

import java.math.BigDecimal;

@Getter
@Setter
public class EquipmentResponse {
    private String id;
    private FactoryResponse factoryResponse;
    private String name;
    private BigDecimal price;
}
