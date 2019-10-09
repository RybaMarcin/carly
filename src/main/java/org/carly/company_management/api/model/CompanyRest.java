package org.carly.company_management.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.api.model.CarRest;
import org.carly.vehicle_management.core.model.Address;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyRest {
    private ObjectId id;
    private String name;
    private int yearOfEstablishment;
    private Long logoId;
    private Address address;
    private List<CarRest> cars;
}
