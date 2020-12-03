package org.carly.api.rest.request;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.dictionaries.BrakeType;

import java.math.BigDecimal;

@Data
public class BrakeRequest {
    private ObjectId id;
    private String name;
    private BrandRequest brand;
    private String preview;
    private BrakeType brakeType;
    private BigDecimal price;
}
