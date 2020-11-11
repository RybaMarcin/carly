package org.carly.api.rest.response;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.*;

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
    private ModelRequest model;
    private double maxSpeed;
    private double accelerate;
    private BigDecimal price;
    private LocalDate yearOfProduction;
    private String transmission;
    private TiresRequest tires;
    private WheelsRequest wheels;
    private WindowsRequest windows;
    private BodyResponse body;
    private double weight;
    private int numberOfDoors;
    private PaintingRequest bodyPainting;
    private List<EquipmentRest> equipment;
}