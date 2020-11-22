package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.dictionaries.EquipmentType;

import java.math.BigDecimal;

@Getter
@Setter
public class EquipmentRequest {
    private ObjectId id;
    private FactoryRequest factoryRequest;
    private String name;
    private BigDecimal price;
    private EquipmentType equipmentType;
}
