package org.carly.core.security.exceptions;

import org.carly.core.shared.exception.ErrorCode;

import java.util.UUID;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

public class TokenBlackListException extends RuntimeException {

    private UUID errorId;
    private ErrorCode errorCode;

    public TokenBlackListException(String message) {
       this(message, UUID.randomUUID(), ErrorCode.TOKEN_IN_BLACK_LIST);
    }

    public TokenBlackListException(String message, UUID errorId, ErrorCode errorCode) {
        super(message);
        this.errorId = errorId;
        this.errorCode = errorCode;
    }
}
