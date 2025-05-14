package com.example.quanlysach.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void testUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setFullname("Test User");

        assertEquals("testuser", userDTO.getUsername());
        assertEquals("Test User", userDTO.getFullname());
    }
}