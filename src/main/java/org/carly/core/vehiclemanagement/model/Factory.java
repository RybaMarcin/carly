package org.carly.core.vehiclemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factory {

    public static final String CARLY_FACTORY_ID = "carlyFactoryId";
    public static final String NAME = "name";
    public static final String RATING = "rating";

    @Field(CARLY_FACTORY_ID)
    private ObjectId carlyFactoryId;

    @Field(NAME)
    private String name;

    @Field(RATING)
    private double rating;
}
