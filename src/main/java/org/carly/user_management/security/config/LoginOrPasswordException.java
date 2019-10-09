package org.carly.user_management.security.config;

import java.util.UUID;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

public class LoginOrPasswordException extends RuntimeException {

    private UUID errorId;
    private String errorCode;

    public LoginOrPasswordException(String message) {
        this(message, UUID.randomUUID(), NOT_FOUND);
    }

    public LoginOrPasswordException(String message, UUID errorId, String errorCode) {
        super(message);
        this.errorId = errorId;
        this.errorCode = errorCode;
    }
}
