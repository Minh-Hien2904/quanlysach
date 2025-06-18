package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.UserResponse;
import com.example.quanlysach.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    @PreAuthorize("fileRole(#request)")
    public UserResponse createUser(@RequestBody UserRequest userRequest,
                                   HttpServletRequest request) {
        return userService.registerUser(userRequest);
    }

    @GetMapping
    @PreAuthorize("fileRole(#request)")
    public List<UserResponse> getAllUsers(HttpServletRequest request) {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public UserResponse getUserById(@PathVariable Long id,
                                    HttpServletRequest request) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public UserResponse updateUser(@PathVariable Long id,
                                   @RequestBody UserRequest userRequest,
                                   HttpServletRequest request) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public void deleteUser(@PathVariable Long id,
                           HttpServletRequest request) {
        userService.deleteUser(id);
    }
}