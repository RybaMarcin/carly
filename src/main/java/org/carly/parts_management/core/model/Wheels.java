package org.carly.parts_management.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Document(collection = "wheels")
public class Wheels extends Part{

    public static final String DIAMETER = "diameter";
    public static final String WEIGHT = "weight";

    @Field(DIAMETER)
    private BigDecimal diameter;

    @Field(WEIGHT)
    private BigDecimal weight;

}
