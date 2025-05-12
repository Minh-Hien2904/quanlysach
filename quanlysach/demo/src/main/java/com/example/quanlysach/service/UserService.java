package com.example.quanlysach.service;

import com.example.quanlysach.model.User;
import java.util.List;

public interface UserService {

    User registerUser(User user);

    boolean authenticateUser(User user);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
