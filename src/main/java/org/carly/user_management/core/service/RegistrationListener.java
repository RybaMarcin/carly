package org.carly.user_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.carly.user_management.core.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private static final String EMAIL_VERIFICATION_TEMPLATE = "verification";
    private static final String VERIFICATION_URL = "http://localhost:8080/user/registrationConfirmation?token=";

    private final TokenService tokenService;
    private final JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;

    public RegistrationListener(TokenService tokenService,
                                JavaMailSender mailSender) {
        this.tokenService = tokenService;
        this.mailSender = mailSender;
    }

    @Bean
    ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mail/MailMessages");
        return messageSource;
    }

    @Bean
    public TemplateEngine emailTemplateEngine() {
        templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());

        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(0));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        final Context ctx = new Context(event.getLocale());
        String token = tokenService.createVerificationToken(user);
        log.info("token: {}", token);

        final String text = VERIFICATION_URL + token;
        ctx.setVariable("registration", text);
        ctx.setVariable("userName", user.getFirstName());

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message; // true = multipart
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject("User Registration in Carly");
            message.setFrom("e.carly.company@gmail.com");
            message.setTo(user.getEmail());
            final String htmlContent = this.templateEngine.process(new ClassPathResource(EMAIL_VERIFICATION_TEMPLATE).getPath(), ctx);
            message.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.info(e.getMessage());
        }
        log.info("Mail was sent");
    }
}