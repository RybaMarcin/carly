package org.carly.api.rest.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.dictionaries.BrakeType;

import java.math.BigDecimal;

@Data
public class BrakeRequest {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private FactoryRequest factoryRequest;
    private String preview;
    private BrakeType brakeType;
    private BigDecimal price;
}
