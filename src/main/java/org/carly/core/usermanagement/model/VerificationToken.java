package org.carly.core.usermanagement.model;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "tokens")
@Getter
public class VerificationToken {
    private ObjectId id;
    private final String token;
    private final User user;
    private final LocalDateTime expiryDate;

    public VerificationToken(User user, String token, LocalDateTime expiryDate) {
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
    }
}