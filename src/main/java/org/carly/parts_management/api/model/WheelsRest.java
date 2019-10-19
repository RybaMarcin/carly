package org.carly.parts_management.api.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class WheelsRest {
    private ObjectId id;
    private String name;
    private BrandRest brand;
    private double diameter;
}
