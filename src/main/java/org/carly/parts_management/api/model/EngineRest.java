package org.carly.parts_management.api.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Data
public class EngineRest {

    private ObjectId id;
    private String name;
    private BigDecimal price;
    private BrandRest brand;
    private double horsePower;
    private double weight;
    private double capacity;
    private int numberOfCylinders;

}
