package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EngineResponse {

    private String id;
    private String name;
    private FactoryResponse brand;
    private Double horsePower;
    private Double weight;
    private Double capacity;
    private String cylinderType;
    private BigDecimal price;
    private LocalDate createDate;
}
