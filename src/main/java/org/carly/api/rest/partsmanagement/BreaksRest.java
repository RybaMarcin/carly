package org.carly.api.rest.partsmanagement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.BreaksType;

import java.math.BigDecimal;

@Data
public class BreaksRest {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private BrandRest brand;
    private String preview;
    private BreaksType breaksType;
    private BigDecimal price;
}
