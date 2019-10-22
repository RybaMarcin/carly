package org.carly.parts_management.api.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
public class EquipmentRest {
    private ObjectId id;
    private BrandRest brand;
    private String name;
    private BigDecimal price;
}
