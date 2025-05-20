package com.example.quanlysach.service.user;

import com.example.quanlysach.dto.user.UserDTO;
import com.example.quanlysach.dto.user.UserRequest;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserRequest userRequest);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);
}
