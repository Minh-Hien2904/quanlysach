package com.example.quanlysach.dto.response;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UserResponseTest {

    @Test
    void testUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername("testuser");
        userResponse.setFullname("Test User");
        userResponse.setAge(25);
        userResponse.setBirthday(LocalDate.of(2000, 1, 1));
        userResponse.setPhoneNumber("0123456789");
        userResponse.setIdentityNumber("123456789");
        userResponse.setAddress("123 Test Street");

        assertEquals("testuser", userResponse.getUsername());
        assertEquals("Test User", userResponse.getFullname());
        assertEquals(25, userResponse.getAge());
        assertEquals(LocalDate.of(2000, 1, 1), userResponse.getBirthday());
        assertEquals("0123456789", userResponse.getPhoneNumber());
        assertEquals("123456789", userResponse.getIdentityNumber());
        assertEquals("123 Test Street", userResponse.getAddress());
    }
}
