package org.carly.api.endpoint;

import org.carly.api.rest.request.LoginRequest;
import org.carly.api.rest.request.SignupRequest;
import org.carly.api.rest.response.MessageResponse;
import org.carly.core.usermanagement.service.TokenBlackListService;
import org.carly.core.usermanagement.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UserService userService;

    @Resource(name = "tokenBlackListService")
    private TokenBlackListService blackListService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/signup-customer")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignupRequest signUpRequest, WebRequest webRequest) {
        return userService.register(signUpRequest, webRequest, false);
    }

    @PostMapping("/signup-company")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody SignupRequest signUpRequest, WebRequest webRequest) {
        return userService.register(signUpRequest, webRequest, true);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String bearerToken) {
        return userService.refreshToken(bearerToken);
    }

    @GetMapping("/registration-confirmation")
    public String confirmRegistration(WebRequest request,
                                      @RequestParam("token") String token) {
        String response = userService.confirmRegistration(request, token);
        return ResponseEntity.ok(response).getBody();
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String bearerToken) {
        blackListService.addTokenToBlackList(bearerToken);
        return ResponseEntity.ok(new MessageResponse("Logout successfully!"));
    }
}
