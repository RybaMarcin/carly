package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Wheels {
    private ObjectId id;
    private String name;
    private Brand brand;
    private double diameter;
}
