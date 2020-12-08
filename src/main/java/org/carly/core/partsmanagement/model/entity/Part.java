package org.carly.core.partsmanagement.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.core.ordermanagement.model.cart.PartType;
import org.carly.core.vehiclemanagement.model.Factory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Part {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String CREATED_DATE = "createdDate";
    public static final String CREATED_BY = "createBy";
    public static final String PREVIEW = "preview";
    public static final String PRICE = "price";
    public static final String FACTORY = "factory";
    public static final String PART_TYPE = "partType";

    @Id
    @Field(ID)
    private ObjectId id;

    @Field(NAME)
    private String name;

    @Field(PART_TYPE)
    private PartType partType;

    @Field(CREATED_BY)
    private String createBy;

    @Field(CREATED_DATE)
    private LocalDateTime createdDate;

    @Field(PREVIEW)
    private String preview;

    @Field(PRICE)
    private BigDecimal price;

    @Field(FACTORY)
    private Factory factory;

}
