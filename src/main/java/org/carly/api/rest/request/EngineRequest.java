package org.carly.api.rest.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.BrandRequest;
import org.carly.core.partsmanagement.model.dictionaries.CylinderType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EngineRequest {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private BrandRequest brand;
    private Double horsePower;
    private Double weight;
    private Double capacity;
    private String cylinderType;
    private BigDecimal price;
    private LocalDate createDate;
}
