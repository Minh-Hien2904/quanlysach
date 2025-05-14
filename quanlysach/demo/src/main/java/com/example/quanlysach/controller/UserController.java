package com.example.quanlysach.controller;

import com.example.quanlysach.dto.UserDTO;
import com.example.quanlysach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library/user")
public class UserController {
    @Autowired
    private UserService userService;

    // Đăng ký người dùng mới
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_CREATE_USER')")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    // Lấy tất cả người dùng
    @GetMapping
    @PreAuthorize("hasRole('ROLE_VIEW_USER')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Lấy người dùng theo ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_VIEW_USER')")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Cập nhật người dùng
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_UPDATE_USER')")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    // Xóa người dùng
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DELETE_USER')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
