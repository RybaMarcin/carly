package org.carly.core.shared.utils.mail_service;

import lombok.extern.slf4j.Slf4j;
//import org.carly.core.config.LoggedUserProvider;
import org.carly.core.config.LoggedUserProvider;
import org.carly.core.usermanagement.model.OnRegistrationCompleteEvent;
import org.carly.core.usermanagement.model.User;
import org.carly.core.usermanagement.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class MailService {

    private static final String EMAIL_VERIFICATION_TEMPLATE = "verification";
    public static final String RESET_PASSWORD_TEMPLATE = "resetpassword";
    private static final String VERIFICATION_URL = "http://localhost:8080/user/registrationConfirmation?token=";

    private final JavaMailSender mailSender;
    private final TokenService tokenService;
    private final LoggedUserProvider loggedUserProvider;
    private SpringTemplateEngine templateEngine;


    public MailService(JavaMailSender mailSender,
                       TokenService tokenService, LoggedUserProvider loggedUserProvider) {
        this.mailSender = mailSender;
        this.tokenService = tokenService;
        this.loggedUserProvider = loggedUserProvider;
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

    public void confirmRegistration(OnRegistrationCompleteEvent event) {
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
            log.info("Mail was sent");
        } catch (MessagingException e) {
            log.info(e.getMessage());
        }
    }

    public void resetPassword(HttpServletRequest request, String url, User user) {
        final Context ctx = new Context(request.getLocale());
        ctx.setVariable("resetPassword", url);
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message;
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject("Reset password");
            message.setFrom("e.carly.company@gmail.com");
            message.setTo(user.getEmail());
            final String htmlContent = templateEngine.process(new ClassPathResource(RESET_PASSWORD_TEMPLATE).getPath(), ctx);
            message.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            log.info("Mail was send");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void send(SimpleMailMessage simpleMailMessage) {
        mailSender.send(simpleMailMessage);
    }

    public SimpleMailMessage constructSimpleMessage(String subject, String body, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(loggedUserProvider.provideCurrent().getEmail());
        return email;
    }
}