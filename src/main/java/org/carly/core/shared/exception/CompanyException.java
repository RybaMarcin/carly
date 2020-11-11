package org.carly.core.shared.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CompanyException extends RuntimeException {

    private UUID errorId;
    private ErrorCode errorCode;

    public CompanyException(String message, UUID errorId, ErrorCode errorCode) {
        super(message);
        this.errorId = errorId;
        this.errorCode = errorCode;
    }

    public CompanyException(String message) {
        this(message, UUID.randomUUID(), ErrorCode.NO_CUSTOMER_ENTITY);
    }

}
