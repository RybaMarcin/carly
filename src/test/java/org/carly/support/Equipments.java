package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Equipment;
import org.carly.vehicle_management.core.model.Brand;

import java.math.BigDecimal;

import static org.carly.shared.utils.builder.Builder.anObject;
import static org.carly.support.Brands.aBrand3;
import static org.carly.support.Brands.aBrand4;

public class Equipments {

    public static final ObjectId EQUIPMENT_ID_1 = new ObjectId("5da1ff32be0ad871841e1085");
    public static final Brand EQUIPMENT_BRAND_1 = aBrand3();
    public static final String NAME_1 = "Speakers";
    public static final BigDecimal PRICE_1 = new BigDecimal(1500.50);

    public static final ObjectId EQUIPMENT_ID_2 = new ObjectId("5da1ff32be0ad871841e9043");
    public static final Brand EQUIPMENT_BRAND_2 = aBrand4();
    public static final String NAME_2 = "Air conditioning";
    public static final BigDecimal PRICE_2 = new BigDecimal(4040.55);

    public static Equipment aEquipment1() {
        return anObject(Equipment.class)
                .with(e -> e.setId(EQUIPMENT_ID_1))
                .with(e -> e.setBrand(EQUIPMENT_BRAND_1))
                .with(e -> e.setName(NAME_1))
                .with(e -> e.setPrice(PRICE_1))
                .build();
    }

    public static Equipment aEquipment2() {
        return anObject(Equipment.class)
                .with(e -> e.setId(EQUIPMENT_ID_2))
                .with(e -> e.setBrand(EQUIPMENT_BRAND_2))
                .with(e -> e.setName(NAME_2))
                .with(e -> e.setPrice(PRICE_2))
                .build();
    }

}
