package org.carly.core.vehiclemanagement.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Brand {
    private ObjectId id;
    private String name;
    private double rating;
}
