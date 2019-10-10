package org.carly.shared.security.exceptions;

public class LoggedUserNotFoundException extends RuntimeException {
    public LoggedUserNotFoundException(String message) {
        super(message);
    }

    public LoggedUserNotFoundException() {
    }
}
