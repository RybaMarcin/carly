package org.carly.core.partsmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.TireType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "tires")
public class Tires extends Part {

    public static final String TIRE_TYPE = "tireType";

    @Field(TIRE_TYPE)
    private TireType tireType;
}
