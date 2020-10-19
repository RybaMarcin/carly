package org.carly.core.usermanagement.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Data
@EqualsAndHashCode(callSuper = true)
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private User user;

    public OnRegistrationCompleteEvent(String appUrl, Locale locale, User user) {
        super(user);
        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
    }
}
