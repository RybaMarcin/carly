package org.carly.core.vehiclemanagement.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.core.customermanagement.model.Customer;

import java.time.LocalDate;

@Data
public class Order {
    private ObjectId id;
    private LocalDate createAt;
    private Status status;
    private Customer customer;
    private Car vehicle;
}
