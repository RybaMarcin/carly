package org.carly.core.partsmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.DiameterType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "wheels")
public class Wheels extends Part{

    public static final String DIAMETER = "diameter";
    public static final String WEIGHT = "weight";

    @Field(DIAMETER)
    private DiameterType diameterType;

    @Field(WEIGHT)
    private Double weight;
}
