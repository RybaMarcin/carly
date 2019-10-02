package org.carly.vehicle_management.api.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Setter
@Getter
public class BrandRest {
    private ObjectId id;
    private String name;
    private BigDecimal rating;
}
