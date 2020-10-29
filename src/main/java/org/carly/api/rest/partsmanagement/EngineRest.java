package org.carly.api.rest.partsmanagement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EngineRest {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private BrandRequest brand;
    private BigDecimal horsePower;
    private BigDecimal weight;
    private BigDecimal capacity;
    private BigDecimal numberOfCylinders;
    private BigDecimal price;
    private LocalDate createDate;
}
