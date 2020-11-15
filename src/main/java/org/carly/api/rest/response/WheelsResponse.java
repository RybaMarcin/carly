package org.carly.api.rest.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.dictionaries.DiameterType;

import java.math.BigDecimal;

@Getter
@Setter
public class WheelsResponse {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private String diameterType;
    private BigDecimal weight;
    private BigDecimal price;
    private String preview;
}
