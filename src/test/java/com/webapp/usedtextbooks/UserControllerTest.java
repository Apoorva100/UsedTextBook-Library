package com.webapp.usedtextbooks;
import com.webapp.usedtextbooks.controller.UserController;
import com.webapp.usedtextbooks.model.Users;
import com.webapp.usedtextbooks.repository.UsersRepository;
import com.webapp.usedtextbooks.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<Users> usersList = Arrays.asList(
                new Users(1L, "User1", "LastName1", "1234567890", "user1@example.com", new Timestamp(System.currentTimeMillis())),
                new Users(2L, "User2", "LastName2", "0987654321", "user2@example.com", new Timestamp(System.currentTimeMillis()))
        );

        when(usersRepository.findAll()).thenReturn(usersList);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUser() throws Exception {
        Users user = new Users(1L, "NewUser", "NewLastName", "9876543210", "newuser@example.com", new Timestamp(System.currentTimeMillis()));

        when(userService.addUser(user)).thenReturn("User added successfully");

        mockMvc.perform(post("/api/v1/users/addUser")
                        .content("{\"id\":1, \"firstName\":\"NewUser\", \"lastName\":\"NewLastName\", \"phoneNumber\":\"9876543210\", \"emailAddress\":\"newuser@example.com\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
