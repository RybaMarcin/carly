package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Tires;

import static org.carly.shared.utils.builder.Builder.anObject;

public class TiresModel {

    public static final ObjectId TIRES_ID = new ObjectId("5da1cd86cd0ad871841e9055");

    public static Tires aTires1() {
        return anObject(Tires.class)
                .with(t -> t.setId(TIRES_ID))
                .build();
    }
}
