package org.carly.parts_management.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BodyRest {
    private ObjectId id;
    private String name;
}
