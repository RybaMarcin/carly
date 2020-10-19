package org.carly.api.rest;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

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
