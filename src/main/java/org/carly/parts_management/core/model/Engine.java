package org.carly.parts_management.core.model;

import lombok.Data;
import org.carly.vehicle_management.core.model.Brand;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document(collection = "engines")
public class Engine extends Part {
    private BigDecimal price;
    private Brand brand;
    private double horsePower;
    private double weight;
    private double capacity;
    private int numberOfCylinders;
    private LocalDate createDate;
}
