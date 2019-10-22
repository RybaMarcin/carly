package org.carly.parts_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
public abstract class Part {

    private ObjectId id;
    private String name;
    private LocalDate createdDate;

}
