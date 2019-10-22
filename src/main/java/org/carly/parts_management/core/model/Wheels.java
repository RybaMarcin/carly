package org.carly.parts_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Brand;

@Data
public class Wheels extends Part{

    private Brand brand;
    private double diameter;
}
