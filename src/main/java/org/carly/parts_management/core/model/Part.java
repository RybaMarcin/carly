package org.carly.parts_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
public abstract class Part {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String CREATED_DATE = "createdDate";

    @Id
    @Field(ID)
    private ObjectId id;

    @Field(NAME)
    private String name;

    @Field(CREATED_DATE)
    private LocalDate createdDate;

}
