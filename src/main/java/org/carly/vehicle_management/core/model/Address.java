package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Address {
    private String city;
    private String country;
    private String street;
    private String number;
    private String zipCode;
}
