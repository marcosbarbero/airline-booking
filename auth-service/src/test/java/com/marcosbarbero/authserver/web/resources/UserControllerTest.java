package com.marcosbarbero.authserver.web.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosbarbero.authserver.helper.Given;
import com.marcosbarbero.authserver.model.entity.User;
import com.marcosbarbero.authserver.model.repository.UserRepository;
import com.marcosbarbero.authserver.web.resources.error.ResponseExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ResponseExceptionHandler exceptionHandler;

    private UserController userController;

    private JacksonTester<User> jsonUser;

    @Before
    public void setUp() {
        this.userController = new UserController(this.userRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(this.exceptionHandler).build();

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void testFindOne_userFound() throws Exception {
        User user = Given.activeUser();
        given(this.userRepository.findOne(user.getId())).willReturn(user);

        ResultActions perform = this.mockMvc.perform(get(UserController.URI + "/{id}", user.getId()).accept(MediaType.APPLICATION_JSON_UTF8));
        perform.andDo(print());
        perform.andExpect(status().isOk());
    }

    @Test
    public void testFindOne_userNotFound() throws Exception {
        given(this.userRepository.findOne(any(Integer.class))).willReturn(null);

        ResultActions perform = this.mockMvc.perform(get(UserController.URI + "/{id}", 1).accept(MediaType.APPLICATION_JSON_UTF8));
        perform.andDo(print());
        perform.andExpect(status().isNotFound());
    }

    @Test
    public void testSave_validUser() throws Exception {
        User user = Given.activeUser();
        given(this.userRepository.save(user)).willReturn(user);

        ResultActions result = this.mockMvc.perform(post(UserController.URI).contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonUser.write(user).getJson()));
        result.andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void testSave_unprocessableEntity() throws Exception {
        User user = Given.activeUser();
        user.setStatus(null);
        given(this.userRepository.save(user)).willReturn(user);

        ResultActions result = this.mockMvc.perform(post(UserController.URI).contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonUser.write(user).getJson()));
        result.andDo(print()).andExpect(status().isUnprocessableEntity());
    }
}
