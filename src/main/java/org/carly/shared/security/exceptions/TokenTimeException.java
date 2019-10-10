package org.carly.shared.security.exceptions;

public class TokenTimeException extends RuntimeException {

    public TokenTimeException() {
    }

    public TokenTimeException(String message) {
        super(message);
    }
}
