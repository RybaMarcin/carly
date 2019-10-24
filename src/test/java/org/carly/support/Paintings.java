package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.PaintType;
import org.carly.vehicle_management.core.model.Painting;

import static org.carly.shared.utils.builder.Builder.anObject;

public class Paintings {

    public static final ObjectId PAINTING_BODY_ID = new ObjectId("5fa1dc86be0ad871841e9033");
    public static final String PAINTING_NAME_1 = "A flower painting";
    public static final PaintType PAINT_TYPE_1 = PaintType.METALLIC;

    public static Painting aPainting1() {
        return anObject(Painting.class)
                .with(p -> p.setId(PAINTING_BODY_ID))
                .with(p -> p.setName(PAINTING_NAME_1))
                .with(p -> p.setType(PAINT_TYPE_1))
                .build();
    }
}
