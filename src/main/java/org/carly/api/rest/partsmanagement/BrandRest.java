package org.carly.api.rest.partsmanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandRest {
    private ObjectId id;
    private String name;
    private double rating;
}
