package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class Customer {
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Car> vehicles;
    private Address address;
}
