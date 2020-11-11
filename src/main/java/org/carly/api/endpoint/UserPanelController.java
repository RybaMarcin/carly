package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.request.AddressRequest;
import org.carly.core.usermanagement.model.Password;
import org.carly.core.usermanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserPanelController {

    private final UserService userService;

    public UserPanelController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('CARLY_CUSTOMER')")
    @PostMapping("/add-address/{id}")
    public ResponseEntity<AddressRequest> addAddressToUserAccount(@PathVariable("id") String userId,
                                                                  @RequestBody AddressRequest addressRequest) {
        return userService.addAddress(new ObjectId(userId), addressRequest);
    }

    @PreAuthorize("hasAnyAuthority('CARLY_CUSTOMER','CARLY_OPERATIONS')")
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(HttpServletRequest request,
                                                @RequestParam("email") String email) {
        return userService.resetUserPassword(request, email);
    }

    @PreAuthorize("hasAnyAuthority('CARLY_CUSTOMER', 'CARLY_OPERATIONS')")
    @GetMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("id") String id,
                                                 @RequestParam("token") String token,
                                                 WebRequest request) {
        String response = userService.changePassword(id, token, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('CHANGE_PASSWORD_PRIVILEGE', 'CARLY_OPERATIONS')")
    @GetMapping("/save-password")
    public ResponseEntity saveNewPassword(@Valid @RequestBody Password password) {
        return userService.saveNewPassword(password.getNewPassword());
    }
}