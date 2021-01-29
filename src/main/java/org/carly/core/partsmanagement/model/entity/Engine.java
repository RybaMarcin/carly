package org.carly.core.partsmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.CylinderType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "engines")
public class Engine extends Part {

    public static final String HORSE_POWER = "horsePower";
    public static final String WEIGHT = "weight";
    public static final String CAPACITY = "capacity";
    public static final String CYLINDER_TYPE = "cylinderType";

    @Field(HORSE_POWER)
    private Double horsePower;

    @Field(WEIGHT)
    private Double weight;

    @Field(CAPACITY)
    private Double capacity;

    @Field(CYLINDER_TYPE)
    private CylinderType cylinderType;
}
