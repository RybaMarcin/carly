package org.carly.core.security.exceptions;

public class TokenTimeException extends RuntimeException {

    public TokenTimeException() {
    }

    public TokenTimeException(String message) {
        super(message);
    }
}
