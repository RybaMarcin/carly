package org.carly.api.endpoint;

import org.carly.api.rest.auth.request.LoginRequest;
import org.carly.api.rest.auth.request.SignupRequest;
import org.carly.core.usermanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, WebRequest webRequest) {
        return userService.register(signUpRequest, webRequest);
    }

    @GetMapping("/registrationConfirmation")
    public String confirmRegistration(WebRequest request,
                                      @RequestParam("token") String token) {
        String response = userService.confirmRegistration(request, token);
        return ResponseEntity.ok(response).getBody();
    }
}
