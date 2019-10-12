package org.carly.vehicle_management.core.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Status {

    NOT_PAID("Not pain"),
    PAID("Paid");

    private String type;
    private static Map<String, Status> statusType;

    Status(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Status of(String status) {
        if (status == null) {
            initStatusType();
        }
        return statusType.get(status);
    }

    private static void initStatusType() {
        statusType = Stream.of(Status.values()).collect(Collectors.toMap(status -> status.type, Function.identity()));
    }
}