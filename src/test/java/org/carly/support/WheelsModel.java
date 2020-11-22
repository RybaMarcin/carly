package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.dictionaries.DiameterType;
import org.carly.core.partsmanagement.model.entity.Wheels;
import org.carly.core.vehiclemanagement.model.Factory;

import static org.carly.core.shared.utils.builder.Builder.anObject;
import static org.carly.support.Brands.aBrand2;

public class WheelsModel {

    public static final ObjectId WHEELS_ID_1 = new ObjectId("5da1ff32be0ad871841e9055");
    public static final String WHEELS_NAME_1 = "Debica Fat";
    public static final Factory WHEELS_FACTORY_1 = aBrand2();
    public static final DiameterType DIAMETER_1 = DiameterType.R16;

    public static Wheels aWheels1() {
        return anObject(Wheels.class)
                .with(w -> w.setId(WHEELS_ID_1))
                .with(w -> w.setName(WHEELS_NAME_1))
                .with(w -> w.setFactory(WHEELS_FACTORY_1))
                .with(w -> w.setDiameterType(DIAMETER_1))
                .build();
    }
}
