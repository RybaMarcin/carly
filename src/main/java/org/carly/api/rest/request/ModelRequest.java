package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class ModelRequest {
    private ObjectId id;
    private String name;
}
