package org.carly.api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.core.security.model.LoggedUser;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.utils.mail_service.MailService;
import org.carly.core.usermanagement.model.AddressRest;
import org.carly.core.usermanagement.model.Password;
import org.carly.core.usermanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
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

    @PreAuthorize("hasAnyAuthority('CARLY_CUSTOMER')")
    @PostMapping("/addAddress/{id}")
    public ResponseEntity<AddressRest> addAddressToUserAccount(@PathVariable("id") String userId,
                                                               @RequestBody AddressRest addressRest) {
        return userService.addAddress(new ObjectId(userId), addressRest);
    }

    @PreAuthorize("hasAnyAuthority('CARLY_CUSTOMER','CARLY_OPERATIONS')")
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(HttpServletRequest request,
                                                @RequestParam("email") String email) {
        return userService.resetUserPassword(request, email);
    }

    @PreAuthorize("hasAnyAuthority('CARLY_CUSTOMER', 'CARLY_OPERATIONS')")
    @GetMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam("id") String id,
                                                 @RequestParam("token") String token,
                                                 WebRequest request) {
        String response = userService.changePassword(id, token, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('CHANGE_PASSWORD_PRIVILEGE', 'CARLY_OPERATIONS')")
    @GetMapping("/savePassword")
    public ResponseEntity saveNewPassword(@Valid @RequestBody Password password) {
        LoggedUser loggedUser = loggedUserProvider.provideUserDetail();
        return userService.saveNewPassword(loggedUser, password.getNewPassword());
    }
}