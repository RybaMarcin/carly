package org.carly.vehicle_management.api.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class ModelRest {
    private ObjectId id;
    private String name;
}
