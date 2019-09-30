package org.carly.company_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "companyChangeRequest")
public class CompanyChangeRequest {

    private ObjectId id;
    private Company company;
    private ChangeRequestStatus status;
    private LocalDate createAt;
    private LocalDate modificationDate;
}

