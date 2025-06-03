package com.example.quanlysach.service.user;

import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);
}
