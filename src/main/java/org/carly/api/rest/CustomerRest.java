package org.carly.api.rest;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CustomerRest {
    private ObjectId id;
    private String code;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<CarRest> cars;
    private LocalDate createAt;
}
