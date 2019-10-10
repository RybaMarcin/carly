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
    private String code;
    private String vinNumber;
    private BrandRest brand;
    private ModelRest model;
    private double maxSpeed;
    private double accelerate;
    private BigDecimal price;
    private LocalDate yearOfProduction;
    private String transmission;
    private TiresRest tires;
    private WheelsRest wheels;
    private WindowsRest windows;
    private BodyRest body;
    private double weight;
    private int numberOfDoors;
    private PaintingRest bodyPainting;
    private EquipmentRest equipment;
}