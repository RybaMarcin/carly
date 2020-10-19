package org.carly.api.rest.partsmanagement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
public class TiresRest {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String name;
    private BigDecimal price;
    private String preview;
}
