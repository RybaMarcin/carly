package org.carly.core.partsmanagement.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.core.vehiclemanagement.model.Brand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
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
