package org.carly.core.partsmanagement.model.dictionaries;

public enum DiameterType {
    R16("R16"),
    R17("R17"),
    R18("R18"),
    R19("R19"),
    R20("R20");

    private String diameterType;

    DiameterType(String diameterType) {
        this.diameterType = diameterType;
    }
}
