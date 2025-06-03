package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.UserResponse;
import com.example.quanlysach.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_CREATE_USER')")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_VIEW_USER')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_VIEW_USER')")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_UPDATE_USER')")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DELETE_USER')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
