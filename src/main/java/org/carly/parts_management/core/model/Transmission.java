package org.carly.parts_management.core.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Transmission {
    AUTOMATIC("AUTOMATIC"),
    MANUAL("MANUAL");

    private String type;
    private static Map<String, Transmission> transmissions;

    Transmission(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Transmission of(String transmission){
        if(transmissions == null){
            initTransmission();
        }
        return transmissions.get(transmission);
    }

    private static void initTransmission() {
        transmissions = Stream.of(Transmission.values()).collect(Collectors.toMap(transmissionType -> transmissionType.type, Function.identity()));
    }

}
