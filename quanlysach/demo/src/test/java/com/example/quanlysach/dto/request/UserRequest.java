package com.example.quanlysach.dto.request;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UserRequestTest {

    @Test
    void testUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setPassword("testpassword");
        userRequest.setFullname("Test User");
        userRequest.setAge(25);
        userRequest.setBirthday(LocalDate.of(2000, 1, 1));
        userRequest.setPhoneNumber("0123456789");
        userRequest.setIdentityNumber("123456789");
        userRequest.setAddress("123 Test Street");

        assertEquals("testuser", userRequest.getUsername());
        assertEquals("testpassword", userRequest.getPassword());
        assertEquals("Test User", userRequest.getFullname());
        assertEquals(25, userRequest.getAge());
        assertEquals(LocalDate.of(2000, 1, 1), userRequest.getBirthday());
        assertEquals("0123456789", userRequest.getPhoneNumber());
        assertEquals("123456789", userRequest.getIdentityNumber());
        assertEquals("123 Test Street", userRequest.getAddress());
    }
}
