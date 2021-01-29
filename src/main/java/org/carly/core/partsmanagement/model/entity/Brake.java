package org.carly.core.partsmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.BrakeType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "brakes")
public class Brake extends Part {

    public static final String BRAKE_TYPE = "brakeType";

    @Field(BRAKE_TYPE)
    private BrakeType brakeType;
}
