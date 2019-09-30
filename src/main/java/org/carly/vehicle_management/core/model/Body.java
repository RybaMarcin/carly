package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Body {
    private ObjectId id;
    private String name;
}
