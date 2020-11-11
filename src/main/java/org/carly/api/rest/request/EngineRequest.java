package org.carly.api.rest.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.BrandRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EngineRequest {

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
