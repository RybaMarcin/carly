package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Model;

import static org.carly.shared.utils.builder.Builder.anObject;

public class Models {

    public static final ObjectId MODEL_ID_1 = new ObjectId("5da1dc86be0ad871841e9013");
    public static final String NAME_1 = "Astra";

    public static Model aModel1() {
        return anObject(Model.class)
                .with(m -> m.setId(MODEL_ID_1))
                .with(m -> m.setName(NAME_1))
                .build();
    }
}
