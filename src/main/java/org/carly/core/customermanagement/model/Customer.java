package org.carly.core.customermanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.core.usermanagement.model.Gender;
import org.carly.core.usermanagement.model.User;
import org.carly.core.vehiclemanagement.model.Car;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Customer extends User {
    private String firstName;
    private String lastName;
    private List<Car> cars;
    private Gender gender;

    public Customer(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }
}
