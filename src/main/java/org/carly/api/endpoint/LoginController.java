package org.carly.api.endpoint;

import org.carly.core.shared.security.model.LoggedUser;
import org.carly.core.shared.security.model.LoggedUserBuilder;
import org.carly.core.usermanagement.model.CarlyUserRest;
import org.carly.core.usermanagement.model.LoginRest;
import org.carly.core.usermanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("login")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRest userRest, HttpServletResponse response) {
        userService.login(userRest, response);
    }

    @GetMapping("/hi")
    public String hi() {
        return "hiiii";
    }

}
