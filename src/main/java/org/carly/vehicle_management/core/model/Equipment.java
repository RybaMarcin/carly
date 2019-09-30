package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "EQUIPMENTS")
public class Equipment {

    private ObjectId id;
    private Brand brand;
    private String name;
    private BigDecimal price;
}
