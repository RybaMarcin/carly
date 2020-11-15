package org.carly.core.partsmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.CylinderType;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "engines")
public class Engine extends Part {

    private Double horsePower;
    private Double weight;
    private Double capacity;
    private CylinderType cylinderType;
}
