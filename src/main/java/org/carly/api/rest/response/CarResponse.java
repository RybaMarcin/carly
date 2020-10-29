package org.carly.api.rest.response;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class CarResponse {
    private ObjectId id;
    private String name;
    private String code;
    private String vinNumber;
    private BrandResponse brand;
    private ModelRest model;
    private double maxSpeed;
    private double accelerate;
    private BigDecimal price;
    private LocalDate yearOfProduction;
    private String transmission;
    private TiresRest tires;
    private WheelsRest wheels;
    private WindowsRest windows;
    private BodyResponse body;
    private double weight;
    private int numberOfDoors;
    private PaintingRest bodyPainting;
    private List<EquipmentRest> equipment;
}