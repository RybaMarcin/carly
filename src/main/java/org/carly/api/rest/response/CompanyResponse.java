package org.carly.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.core.vehiclemanagement.model.Address;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyResponse {
    private ObjectId id;
    private String name;
    private String brand;
    //todo: Commented temporary
//    private int yearOfEstablishment;
//    private Long logoId;
    private Address address;
}
