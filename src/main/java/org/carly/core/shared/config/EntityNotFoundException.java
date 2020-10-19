package org.carly.core.shared.config;

import java.util.UUID;

import static org.carly.core.shared.config.ErrorCode.NOT_FOUND;

public class EntityNotFoundException extends RuntimeException {

    private UUID errorId;
    private ErrorCode errorCode;

    public EntityNotFoundException(String message) {
        this(message, UUID.randomUUID(), NOT_FOUND);
    }

    public EntityNotFoundException(String message, UUID errorId, ErrorCode errorCode) {
        super(message);
        this.errorId = errorId;
        this.errorCode = errorCode;
    }
}
