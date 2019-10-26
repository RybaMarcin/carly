package org.carly.parts_management.core.model;

import lombok.Data;
import org.carly.vehicle_management.core.model.Brand;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "wheels")
public class Wheels extends Part{
//    private Brand brand;
    private BigDecimal diameter;
    private BigDecimal weight;
    private BigDecimal price;
    private String preview;
}
