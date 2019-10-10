package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Address {
    private ObjectId id;
    private String street;
    private String flat;
    private String town;
    private String zipCode;
    private String country;
}
