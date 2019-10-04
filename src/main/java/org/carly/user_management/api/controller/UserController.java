package org.carly.user_management.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.carly.user_management.api.model.CarlyUserRest;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.service.RegistrationListener;
import org.carly.user_management.core.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final RegistrationListener registrationListener;

    public UserController(UserService userService,
                          RegistrationListener registrationListener) {
        this.userService = userService;
        this.registrationListener = registrationListener;
    }

    @PostMapping("/registration")
    public User registerUserAccount(@Valid @RequestBody UserRest userRest,
                                    BindingResult result,
                                    WebRequest request) {
        User registered = null;
        if (!result.hasErrors()) {
            registered = userService.createUser(userRest, request);
        }
        if (registered == null) {
            result.rejectValue(userRest.getEmail(), "Account with that email already exists!");
            throw new IllegalArgumentException("Cannot registration user");
        }
        return registered;
    }

    @GetMapping("/registrationConfirmation")
    public String confirmRegistration(WebRequest request,
                                      @RequestParam("token") String token) {
        return userService.confirmRegistration(request, token);
    }

    @GetMapping("/login")
    public CarlyUserRest login(@Valid @RequestBody UserRest userRest,
                               BindingResult result) {
        return userService.login(userRest);
    }


    //for mail test
    @GetMapping("/send")
    public void sendMail() {
        registrationListener.sendSimpleEmail("d.szwajkos@gmail.com", "Carly company mail sender", "Nasz mail który bedzie potwierdzał rejerstracje:P");
    }
}