package org.carly.core.partsmanagement.model.dictionaries;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EquipmentType {
    RADIO("Radio"),
    GPS("GPS"),
    DVD("DVD"),
    MP3("MP3");

    private String type;
    private static Map<String, EquipmentType> equipmentTypes;

    EquipmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EquipmentType of(String typeOf) {
        if(equipmentTypes == null) {
            initEquipmentType();
        }
        return equipmentTypes.get(typeOf);
    }

    private static void initEquipmentType() {
        equipmentTypes = Stream.of(EquipmentType.values())
                .collect(Collectors.toMap(equipmentType -> equipmentType.type, Function.identity()));
    }

}
