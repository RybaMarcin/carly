package org.carly.api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.core.config.LoggedUserProvider;
import org.carly.core.shared.security.model.LoggedUser;
import org.carly.core.shared.utils.mail_service.MailService;
import org.carly.core.usermanagement.model.AddressRest;
import org.carly.core.usermanagement.model.Password;
import org.carly.core.usermanagement.model.User;
import org.carly.core.usermanagement.model.UserRest;
import org.carly.core.usermanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final LoggedUserProvider loggedUserProvider;

    public UserController(UserService userService,
                          MailService mailService,
                          LoggedUserProvider loggedUserProvider) {
        this.userService = userService;
        this.mailService = mailService;
        this.loggedUserProvider = loggedUserProvider;
    }

    @GetMapping("/hello")
    public String hi(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return "hi";
    }

    @PreAuthorize("hasAnyAuthority('CARLY_CUSTOMER')")
    @GetMapping("/hello2")
    public String hi2(HttpServletRequest request) {
        Principal userPrincipal = request.getUserPrincipal();
        return "hix";
    }

    @PostMapping("/registration")
    public User registerUserAccount(@Valid @RequestBody UserRest userRest,
                                    BindingResult result,
                                    WebRequest request) {
        return userService.createUser(userRest, request);
    }

    @GetMapping("/registrationConfirmation")
    public String confirmRegistration(WebRequest request,
                                      @RequestParam("token") String token) {
        String response = userService.confirmRegistration(request, token);
        return ResponseEntity.ok(response).getBody();
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @PostMapping("/addAddress/{id}")
    public ResponseEntity addAddressToUserAccount(@PathVariable("id") String userId,
                                                  @RequestBody AddressRest addressRest) {
        return userService.addAddress(new ObjectId(userId), addressRest);
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER','OPERATIONS')")
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(HttpServletRequest request,
                                                @RequestParam("email") String email) {
        return userService.resetUserPassword(request, email);
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'OPERATIONS')")
    @GetMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam("id") String id,
                                                 @RequestParam("token") String token,
                                                 WebRequest request) {
        String response = userService.changePassword(id, token, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('CHANGE_PASSWORD_PRIVILEGE', 'OPERATIONS')")
    @GetMapping("/savePassword")
    public ResponseEntity saveNewPassword(@Valid @RequestBody Password password) {
        LoggedUser loggedUser = loggedUserProvider.provideCurrent();
        return userService.saveNewPassword(loggedUser, password.getNewPassword());
    }
}