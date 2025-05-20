package com.example.quanlysach.service;

import com.example.quanlysach.dto.user.UserDTO;
import com.example.quanlysach.dto.user.UserRequest;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.repository.UserRepository;
import com.example.quanlysach.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    // Inject UserServiceImpl chứ không phải interface UserService
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Dùng UserRequest làm input cho method registerUser
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setPassword("testpassword");
        userRequest.setFullname("Test User");
        // ... có thể set thêm các trường cần thiết

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedpassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO savedUser = userService.registerUser(userRequest);

        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        // Mật khẩu đã được mã hóa lưu trong entity User, không trả ra DTO nên không so sánh password ở đây
    }
}
