package org.carly.core.customermanagement.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.core.vehiclemanagement.model.Car;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Customer {
    private ObjectId id;
    private String code;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Car> cars;
    private Gender gender;
    private LocalDate createAt;
}
