package org.carly.company_management.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Address;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyRest {
    private ObjectId id;
    private String name;
    private String brand;
    //todo: Commented temporary
//    private int yearOfEstablishment;
//    private Long logoId;
    private Address address;
}
