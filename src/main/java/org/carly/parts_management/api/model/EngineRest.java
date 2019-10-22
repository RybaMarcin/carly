package org.carly.parts_management.api.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EngineRest {

    private ObjectId id;
    private String name;
    private BrandRest brand;
    private double horsePower;
    private double weight;
    private double capacity;
    private int numberOfCylinders;
    private BigDecimal price;
    private LocalDate createDate;
}
