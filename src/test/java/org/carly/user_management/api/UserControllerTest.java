//package org.carly.user_management.api;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.carly.shared.security.config.LoggedUserProvider;
//import org.carly.shared.utils.DataLoader;
//import org.carly.user_management.api.controller.UserController;
//import org.carly.user_management.api.model.UserRest;
//import org.carly.user_management.core.model.User;
//import org.carly.user_management.core.repository.UserRepository;
//import org.carly.user_management.core.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.web.context.request.WebRequest;
//
//import java.io.IOException;
//
//import static org.carly.support.Users.aUser1;
//import static org.carly.support.Users.aUserRest1;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = UserController.class)
//public class UserControllerTest {
//
//    private static final String USER_PATH = "/user";
//    private static final String REGISTRATION_PATH = USER_PATH + "/registration";
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Autowired
//    protected MockMvc mockMvc;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private LoggedUserProvider loggedUserProvider;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        DataLoader.loadData(userRepository, "org/carly/support/data/user.json", User.class);
//    }
//
//    //todo finish integration test.
//    @Test
//    void shouldSuccessfullyRegistrationNewUser() throws Exception {
//        //given
//        UserRest userRest = aUserRest1();
//        User user = aUser1();
//        WebRequest request = mock(WebRequest.class);
//        //and
//        when(userService.createUser(userRest, request)).thenReturn(user);
//        //when
//        ResultActions results = mockMvc.perform(post(REGISTRATION_PATH)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(objectMapper.writeValueAsBytes(userRest)))
//                .andDo(print());
//        results.andExpect(status().isOk());
//    }
//}