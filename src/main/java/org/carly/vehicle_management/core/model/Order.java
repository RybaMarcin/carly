package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
public class Order {
    private ObjectId id;
    private LocalDate createAt;
    private Status status;
    private Customer customer;
    private Car vehicle;
}
