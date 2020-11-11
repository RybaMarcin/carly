package org.carly.api.rest.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
@Getter
@Setter
public class WheelsRequest {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
//    private BrandRest brand;
    private BigDecimal diameter;
    private BigDecimal weight;
    private BigDecimal price;
    private String preview;
}
