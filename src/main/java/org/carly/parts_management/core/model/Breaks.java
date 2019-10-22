package org.carly.parts_management.core.model;

import lombok.Data;
import org.carly.vehicle_management.core.model.Brand;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "breaks")
public class Breaks extends Part {
    private Brand brand;
    private String preview;
    private BreaksType breaksType;
    private BigDecimal price;
}
