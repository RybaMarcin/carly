package org.carly.core.partsmanagement.model.dictionaries;

public enum  TireType {
    REINFORCED("Reinforced"),
    ALL_ROAD("All road");

    private String tireType;

    TireType(String tireType) {
        this.tireType = tireType;
    }

    public String getTireType() {
        return tireType;
    }
}
