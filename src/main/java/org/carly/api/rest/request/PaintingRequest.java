package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class PaintingRequest {
    private ObjectId id;
    private String name;
    private String type;
}
