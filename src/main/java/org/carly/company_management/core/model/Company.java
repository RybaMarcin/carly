package org.carly.company_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Address;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "company")
public class Company {

    public static final String ID = "id";

    //todo: Fields commented temporarily.

    @Value(ID)
    private ObjectId id;
    private String name;
    private String brand;
    //    private int yearOfEstablishment;
//    private Long logoId;
    private Address address;
//    private List<Car> cars;
    private ChangeRequestStatus requestStatus;
}
