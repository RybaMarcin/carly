package org.carly.parts_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Brand;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "equipment")
public class Equipment {
    private ObjectId id;
    private Brand brand;
    private String name;
    private BigDecimal price;
}
