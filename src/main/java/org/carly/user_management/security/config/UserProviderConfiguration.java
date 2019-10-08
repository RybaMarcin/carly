package org.carly.user_management.security.config;

import org.carly.user_management.core.config.LoggedUserProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserProviderConfiguration {

    @Bean
    public LoggedUserProvider loggedUserProvider(){
        return new LoggedUserProvider();
    }
}
