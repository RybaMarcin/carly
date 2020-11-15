package org.carly.core.shared.exception;

public enum ErrorCode {

    ENTITY_NOT_FOUND("Entity not found."),
    NO_CUSTOMER_ENTITY("Entity is not customer one"),
    TOKEN_IN_BLACK_LIST("Token is in black list");

    private String description;

    ErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
