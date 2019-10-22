package org.carly.parts_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Brand;

import java.math.BigDecimal;

@Data
public class Engine {
    private ObjectId id;
    private String name;
    private BigDecimal price;
    private Brand brand;
    private double horsePower;
    private double weight;
    private double capacity;
    private int numberOfCylinders;
}
