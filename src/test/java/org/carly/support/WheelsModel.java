package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Wheels;
import org.carly.vehicle_management.core.model.Brand;

import java.math.BigDecimal;

import static org.carly.shared.utils.builder.Builder.anObject;
import static org.carly.support.Brands.aBrand2;

public class WheelsModel {

    public static final ObjectId WHEELS_ID_1 = new ObjectId("5da1ff32be0ad871841e9055");
    public static final String WHEELS_NAME_1 = "Debica Fat";
    public static final Brand WHEELS_BRAND_1 = aBrand2();
    public static final BigDecimal DIAMETER_1 = new BigDecimal("17.5");

    public static Wheels aWheels1() {
        return anObject(Wheels.class)
                .with(w -> w.setId(WHEELS_ID_1))
                .with(w -> w.setName(WHEELS_NAME_1))
                .with(w -> w.setBrand(WHEELS_BRAND_1))
                .with(w -> w.setDiameter(DIAMETER_1))
                .build();
    }
}
