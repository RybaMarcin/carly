package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Body;

import static org.carly.shared.utils.builder.Builder.anObject;

public class Bodies {

    public static final ObjectId BODY_ID_1 = new ObjectId("5cc1cd86be0ad871841e9083");
    public static final String BODY_NAME = "Opel Body";

    public static Body aBody1() {
        return anObject(Body.class)
                .with(b -> b.setId(BODY_ID_1))
                .with(b -> b.setName(BODY_NAME))
                .build();
    }
}
