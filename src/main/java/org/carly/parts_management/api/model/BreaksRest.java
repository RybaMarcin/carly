package org.carly.parts_management.api.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.BreaksType;

import java.math.BigDecimal;

@Data
public class BreaksRest {
    private ObjectId id;
    private String name;
    private BrandRest brand;
    private String preview;
    private BreaksType breaksType;
    private BigDecimal price;
}
