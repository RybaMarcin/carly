package org.carly.vehicle_management.api.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class CarChangeRequestRest {
    private ObjectId carId;
    private String changeRequestStatus;
    private String declinedReason;
}