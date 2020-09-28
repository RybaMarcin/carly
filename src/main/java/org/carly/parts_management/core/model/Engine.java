package org.carly.parts_management.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "engines")
public class Engine extends Part {

    private BigDecimal horsePower;
    private BigDecimal weight;
    private BigDecimal capacity;
    private BigDecimal numberOfCylinders;
}
