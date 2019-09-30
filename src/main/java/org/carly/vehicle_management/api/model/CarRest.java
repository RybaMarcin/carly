package org.carly.vehicle_management.api.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CarRest {
    private ObjectId id;
    private String name;
    private String vinNumber;
    private Brand brand;
    private Model model;
    private double maxSpeed;
    private double accelerate;
    private BigDecimal price;
    private LocalDate yearOfProduction;
    private String transmission;
    private Tires tires;
    private Wheels wheels;
    private Windows windows;
    private Body body;
    private double weight;
    private int numberOfDoors;
    private Painting bodyPainting;
    private Equipment equipment;
}