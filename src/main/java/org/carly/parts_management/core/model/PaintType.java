package org.carly.parts_management.core.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PaintType {
    METALLIC("Metallic"),
    MATTE("Matte");

    private  String type;
    private static Map<String, PaintType> paintTypes;

    PaintType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static PaintType of(String typeOf){
        if(paintTypes==null){
            initPaintType();
        }
        return paintTypes.get(typeOf);
    }

    private static void initPaintType() {
        paintTypes = Stream.of(PaintType.values()).collect(Collectors.toMap(paintType -> paintType.type, Function.identity()));
    }

}
