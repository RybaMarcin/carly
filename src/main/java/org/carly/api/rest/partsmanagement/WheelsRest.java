package org.carly.api.rest.partsmanagement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Data
public class WheelsRest {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
//    private BrandRest brand;
    private BigDecimal diameter;
    private BigDecimal weight;
    private BigDecimal price;
    private String preview;
}
