package org.carly.vehicle_management.api.model;

import lombok.Data;

@Data
public class CarChangeRequestRest {
    private Long carId;
    private ChangeRequestStatusRest changeRequestStatus;
    private String declinedReason;
}