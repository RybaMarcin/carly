package org.carly.core.partsmanagement.model.dictionaries;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BrakeType {
    HYDRAULIC("Hydraulic"),
    MECHANIC("Mechanic"),
    PNEUMATIC("Pneumatic");

    private String type;
    private static Map<String, BrakeType> breaksTypes;

    BrakeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static BrakeType of(String typeOf) {
        if(breaksTypes == null) {
            initBreaksType();
        }
        return breaksTypes.get(typeOf);
    }


    private static void initBreaksType() {
        breaksTypes = Stream.of(BrakeType.values()).collect(Collectors.toMap(brakeType -> brakeType.type, Function.identity()));
    }

}
