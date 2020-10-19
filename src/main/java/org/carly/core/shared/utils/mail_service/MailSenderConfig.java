package org.carly.core.shared.utils.mail_service;

import org.bson.types.ObjectId;
import org.carly.core.shared.config.EntityNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Configuration
public class MailSenderConfig {

    private static final String JAVA_MAIL_FILE = "classpath:mail/javamail.properties";
    private static final String MESSAGES_FILE = "classpath:mail/messages";
    private static final ObjectId MAIL_PROPERTIES = new ObjectId("5da0715c1c9d440000f105fe");

    private final ApplicationContext applicationContext;
    private final MailDataRepository repository;

    public MailSenderConfig(ApplicationContext applicationContext, MailDataRepository repository) {
        this.applicationContext = applicationContext;
        this.repository = repository;
    }

    @PostConstruct
    public MailData getEmailPassword() {
        return repository.findById(MAIL_PROPERTIES).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGES_FILE);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public JavaMailSender mailSender() throws IOException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(getEmailPassword().getHost());
        mailSender.setPort(getEmailPassword().getPort());
        mailSender.setUsername(getEmailPassword().getEmail());
        mailSender.setPassword(getEmailPassword().getPassword());

        Properties javaMailProperties = new Properties();
        javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }
}