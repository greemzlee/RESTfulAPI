package ru.gazis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.gazis.bean.User;
import ru.gazis.dao.UserDao;
import ru.gazis.service.UserService;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static com.sun.javaws.JnlpxArgs.verify;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/app-context.xml")
@WebAppConfiguration
public class UserControllerTest extends TestCase {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    private ObjectMapper mapper = new ObjectMapper();
    private User user;


//    public UserControllerTest( ) {
//        this.controller = new UserController();
//    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        user = new User();
    }

    @Test
    public void testGetUser() throws Exception {
        User user = userService.getUser(1);
        mockMvc.perform(get("/user/{userId}", user.getUserId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("userId").value(user.getUserId()));
    }

    @Test
    public void testGetUserNotFound() throws Exception {
        mockMvc.perform(get("/user/{userId}", 10000))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetUserNotFoundWithNegativeId() throws Exception {
        mockMvc.perform(get("/user/{userId}", -1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetUserNotFoundWithNullId() throws Exception {
        mockMvc.perform(get("/user/{userId}", 0))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User(1, "UPDATEUSERNAME", "WWW", "EEE");
        String json = mapper.writeValueAsString(user);
        mockMvc.perform(put("/user/{userId}", user.getUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserNotFound() throws Exception {
        User user = new User(10000, "UPDATEUSERNAME", "WWW", "EEE");
        String json = mapper.writeValueAsString(user);
        mockMvc.perform(put("/user/{userId}", user.getUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}