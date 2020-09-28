package org.carly.parts_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Brand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public abstract class Part {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String CREATED_DATE = "createdDate";
    public static final String PREVIEW = "preview";
    public static final String PRICE = "price";
    public static final String BRAND = "brand";

    @Id
    @Field(ID)
    private ObjectId id;

    @Field(NAME)
    private String name;

    @Field(CREATED_DATE)
    private LocalDate createdDate;

    @Field(PREVIEW)
    private String preview;

    @Field(PRICE)
    private BigDecimal price;

    @Field(BRAND)
    private Brand brand;

}
