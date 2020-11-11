package org.carly.api.rest.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
public class WheelsResponse {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private BigDecimal diameter;
    private BigDecimal weight;
    private BigDecimal price;
    private String preview;
}
