package org.carly.core.shared.utils.mail_service;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("mailData")
public class MailData {
    private String host;
    private int port;
    private String email;
    private String password;
}
