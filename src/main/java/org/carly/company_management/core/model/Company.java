package org.carly.company_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Address;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "company")
public class Company {

    public static final String ID = "id";
    public static final String NAME = "name";

    //todo: Fields commented temporarily.

    @Id
    @Field(ID)
    private ObjectId id;

    @Field(NAME)
    private String name;
    private String brand;
    //    private int yearOfEstablishment;
//    private Long logoId;
    private Address address;
//    private List<Car> cars;
    private ChangeRequestStatus requestStatus;
}
