package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.api.rest.response.CarResponse;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CustomerResponse {
    private ObjectId id;
    private String code;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<CarResponse> cars;
    private LocalDate createAt;
}
