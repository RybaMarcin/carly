package org.carly.ordering_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.model.Customer;
import org.carly.vehicle_management.core.model.Status;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = "orders")
public class Order {

    public static final String ORDER_ID = "_id";
    public static final String ORDER_CREATE_AT = "createAt";
    public static final String ORDER_MODIFIED_AT = "modifiedAt";
    public static final String ORDER_STATUS = "status";
    public static final String ORDER_CUSTOMER = "customer";
    public static final String ORDER_CAR = "car";

    @Field(ORDER_ID)
    private ObjectId id;
    @Field(ORDER_CREATE_AT)
    private LocalDate createAt;
    @Field(ORDER_MODIFIED_AT)
    private LocalDate modifiedAt;
    @Field(ORDER_STATUS)
    private Status status;
    @Field(ORDER_CUSTOMER)
    private ObjectId userId;
    @Field(ORDER_CAR)
    private ObjectId carId;
}