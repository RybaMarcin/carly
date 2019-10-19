package org.carly.parts_management.api.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class WindowsRest {
    private ObjectId id;
    private String name;
    private String color;
}
