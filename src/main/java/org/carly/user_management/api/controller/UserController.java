package org.carly.user_management.api.controller;

import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.service.UserService;
import org.carly.user_management.core.service.VerificationService;
import org.carly.user_management.security.LoggedUser;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController("/user")
public class UserController {

    private final UserService userService;
    private final VerificationService verificationService;

    public UserController(UserService userService, VerificationService verificationService) {
        this.userService = userService;
        this.verificationService = verificationService;
    }

    @PostMapping("/registration")
    public User registerUserAccount(@Valid @RequestBody UserRest userRest,
                                    BindingResult result,
                                    WebRequest request) {
        User registered = null;
        if (!result.hasErrors()) {
            registered = userService.createUser(userRest);
            verificationService.publish(registered, request);

        }
        if (registered == null) {
            result.rejectValue(userRest.getEmail(), "Account with that email already exists!");
            throw new IllegalArgumentException("Cannot registration user");
        }
        return registered;
    }

    @GetMapping("/login")
    public LoggedUser login(@Valid @RequestBody UserRest userRest,
                            BindingResult result) {
        return userService.login(userRest);
    }
}
