package com.example.quanlysach.controller;

import com.example.quanlysach.dto.user.UserDTO;
import com.example.quanlysach.dto.user.UserRequest;
import com.example.quanlysach.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testCreateUser() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setPassword("testpassword");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        // Có thể set các trường khác trong userDTO nếu cần

        // Mock khi gọi service với UserRequest sẽ trả về UserDTO
        when(userService.registerUser(any(UserRequest.class))).thenReturn(userDTO);

        mockMvc.perform(post("/api/v1/library/user/create")
                        .contentType("application/json")
                        .content("{\"username\":\"testuser\", \"password\":\"testpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }
}
