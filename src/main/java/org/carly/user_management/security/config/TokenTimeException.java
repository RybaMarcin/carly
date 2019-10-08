package org.carly.user_management.security.config;

public class TokenTimeException extends RuntimeException {

    public TokenTimeException() {
    }

    public TokenTimeException(String message) {
        super(message);
    }
}
