package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "carChangeRequest")
public class CarChangeRequest {
    private ObjectId id;
    private Car car;
    private ChangeRequestStatus status;
    private LocalDate createDate;
    private LocalDate lastModificationDate;
    private String statusChangeReason;
}
