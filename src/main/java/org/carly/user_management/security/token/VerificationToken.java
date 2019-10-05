package org.carly.user_management.security.token;

import lombok.Data;
import org.carly.user_management.core.model.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Document(collection = "tokens")
@Data
public class VerificationToken {
    private String token;
    private User user;
    private LocalDateTime expiryDate;

    public VerificationToken(User user, String token, LocalDateTime expiryDate) {
        this.user = user;
        this.token = token;
        this.expiryDate=expiryDate;
    }
}