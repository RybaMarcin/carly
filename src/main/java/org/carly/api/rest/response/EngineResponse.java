package org.carly.api.rest.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EngineResponse {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private BrandResponse brand;
    private Double horsePower;
    private Double weight;
    private Double capacity;
    private String cylinderType;
    private BigDecimal price;
    private LocalDate createDate;
}
