package org.carly.core.partsmanagement.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BreaksType {
    HYDRAULIC("Hydraulic"),
    MECHANIC("Mechanic"),
    PNEUMATIC("Pneumatic");

    private String type;
    private static Map<String, BreaksType> breaksTypes;

    BreaksType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static BreaksType of(String typeOf) {
        if(breaksTypes == null) {
            initBreaksType();
        }
        return breaksTypes.get(typeOf);
    }


    private static void initBreaksType() {
        breaksTypes = Stream.of(BreaksType.values()).collect(Collectors.toMap(breaksType -> breaksType.type, Function.identity()));
    }

}
