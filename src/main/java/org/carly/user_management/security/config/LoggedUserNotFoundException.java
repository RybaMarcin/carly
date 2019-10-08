package org.carly.user_management.security.config;

public class LoggedUserNotFoundException extends RuntimeException {
    public LoggedUserNotFoundException(String message) {
        super(message);
    }

    public LoggedUserNotFoundException() {
    }
}
