package org.carly.ordering_management.api.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.api.model.CarRest;

import java.time.LocalDate;

@Getter
@Setter
public class OrderRest {
    private ObjectId id;
    private LocalDate createAt;
    private String status;
    private ObjectId userId;
    private ObjectId carId;
}
