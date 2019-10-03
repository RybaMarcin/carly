package org.carly.user_management.security.token;

import lombok.Data;
import org.carly.user_management.core.model.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


@Document(collection = "tokens")
@Data
public class VerificationToken {
    private static final int EXPIRATION = 6 * 24;

    private String token;
    private User user;
    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}