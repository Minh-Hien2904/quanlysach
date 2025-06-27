package com.example.quanlysach.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserModel() {
        User user = new User();
        user.setUsername("testuser");
        user.setFullname("Test User");
        user.setPhoneNumber("123456789");
        user.setIdentityNumber("987654321");
        user.setAge(25);
        user.setBirthday(LocalDate.parse("2000-01-01"));
        user.setAddress("123 Test Street");

        assertEquals("testuser", user.getUsername());
        assertEquals("Test User", user.getFullname());
        assertEquals("123456789", user.getPhoneNumber());
        assertEquals("987654321", user.getIdentityNumber());
        assertEquals(25, user.getAge());
        assertEquals(LocalDate.parse("2000-01-01"), user.getBirthday());
        assertEquals("123 Test Street", user.getAddress());
    }
}
