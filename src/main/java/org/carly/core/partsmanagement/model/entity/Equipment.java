package org.carly.core.partsmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.EquipmentType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "equipment")
public class Equipment extends Part {

    public static final String TYPE = "type";

    @Field(TYPE)
    private EquipmentType type;
}
