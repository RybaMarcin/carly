package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.core.vehiclemanagement.model.Factory;

import static org.carly.core.shared.utils.builder.Builder.anObject;

public class Brands {

    public static final ObjectId BRAND_ID_1 = new ObjectId("5da1cd86be0ad871841e9010");
    public static final String NAME_1 = "Opel";
    public static final double RATING_1 = 7.8;

    public static final ObjectId BRAND_ID_2 = new ObjectId("5da1cd32be0ad871841e9067");
    public static final String NAME_2 = "Debica";
    public static final double RATING_2 = 6.8;

    public static final ObjectId BRAND_ID_3 = new ObjectId("5da1ff32be0ad871841e9085");
    public static final String NAME_3 = "Sony";
    public static final double RATING_3 = 8.8;

    public static final ObjectId BRAND_ID_4 = new ObjectId("5da1ff32be0ad871841e9087");
    public static final String NAME_4 = "Ice Men";
    public static final double RATING_4 = 3.8;


    public static Factory aBrand1() {
        return anObject(Factory.class)
                .with(b -> b.setCarlyFactoryId(BRAND_ID_1))
                .with(b -> b.setName(NAME_1))
                .with(b -> b.setRating(RATING_1))
                .build();
    }

    public static Factory aBrand2() {
        return anObject(Factory.class)
                .with(b -> b.setCarlyFactoryId(BRAND_ID_2))
                .with(b -> b.setName(NAME_2))
                .with(b -> b.setRating(RATING_2))
                .build();
    }

    public static Factory aBrand3() {
        return anObject(Factory.class)
                .with(b -> b.setCarlyFactoryId(BRAND_ID_3))
                .with(b -> b.setName(NAME_3))
                .with(b -> b.setRating(RATING_3))
                .build();
    }
    public static Factory aBrand4() {
        return anObject(Factory.class)
                .with(b -> b.setCarlyFactoryId(BRAND_ID_4))
                .with(b -> b.setName(NAME_4))
                .with(b -> b.setRating(RATING_4))
                .build();
    }
}
