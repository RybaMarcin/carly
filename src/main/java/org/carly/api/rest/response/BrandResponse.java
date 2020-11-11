package org.carly.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandResponse {
    private ObjectId id;
    private String name;
    private double rating;
}
