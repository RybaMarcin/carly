package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "cars")
public class Car {

    public static final String ID = "id";
    public static final String NAME = "name";

    @Field(ID)
    private ObjectId id;
    @Field(NAME)
    private String name;
    private LocalDate createAt;
    private LocalDate modifyAt;
    private String vinNumber;
    private ChangeRequestStatus requestStatus;
    private String code;
    private Brand brand;
    private Model model;
    private double maxSpeed;
    private double accelerate;
    private BigDecimal price;
    private LocalDate yearOfProduction;
    private Transmission transmission;
    private Tires tires;
    private Wheels wheels;
    private Windows windows;
    private Body body;
    private double weight;
    private int numberOfDoors;
    private Painting bodyPainting;
    private List<Equipment> equipment;
}