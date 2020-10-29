package org.carly.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BodyResponse {
    private ObjectId id;
    private String name;
}
