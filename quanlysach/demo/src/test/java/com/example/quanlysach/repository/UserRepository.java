package com.example.quanlysach.repository;

import com.example.quanlysach.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setFullname("Test User");
        user.setPhoneNumber("123456789");
        user.setIdentityNumber("987654321");
        user.setAge(25);
        user.setBirthday(LocalDate.parse("2000-01-01"));
        user.setAddress("123 Test Street");

        userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }
}
