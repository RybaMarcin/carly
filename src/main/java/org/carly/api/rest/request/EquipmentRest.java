package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.BrandRequest;

import java.math.BigDecimal;

@Getter
@Setter
public class EquipmentRest {
    private ObjectId id;
    private BrandRequest brand;
    private String name;
    private BigDecimal price;
}
