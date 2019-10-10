package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Data
public class Brand {
    private ObjectId id;
    private String name;
    private BigDecimal rating;
}
