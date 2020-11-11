package org.carly.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyResponse {
    private String id;
    private String companyName;
    private String email;
    private String phone;
    private AddressResponse address;
}
