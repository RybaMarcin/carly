package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class WindowsResponse {
    private ObjectId id;
    private String name;
    private String color;
}
