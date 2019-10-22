package org.carly.parts_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Painting {
    private ObjectId id;
    private String name;
    private PaintType type;
}
