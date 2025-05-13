package com.example.quanlysach.controller;

import com.example.quanlysach.dto.UserDTO;
import com.example.quanlysach.model.User;
import com.example.quanlysach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/library/user")
public class UserController {
    @Autowired
    private UserService userService;

    // Đăng ký người dùng mới
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_CREATE_USER')")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        // Chuyển UserDTO thành User và lưu
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getFullname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setIdentityNumber(userDTO.getIdentityNumber());
        user.setAge(userDTO.getAge());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());

        User savedUser = userService.registerUser(user);

        // Chuyển User thành UserDTO để trả về
        UserDTO response = new UserDTO();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setFullname(savedUser.getFullname());
        response.setPhoneNumber(savedUser.getPhoneNumber());
        response.setIdentityNumber(savedUser.getIdentityNumber());
        response.setAge(savedUser.getAge());
        response.setBirthday(savedUser.getBirthday());
        response.setAddress(savedUser.getAddress());

        return response;
    }

    // Lấy tất cả người dùng
    @GetMapping
    @PreAuthorize("hasRole('ROLE_VIEW_USER')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setFullname(user.getFullname());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setIdentityNumber(user.getIdentityNumber());
            userDTO.setAge(user.getAge());
            userDTO.setBirthday(user.getBirthday());
            userDTO.setAddress(user.getAddress());
            return userDTO;
        }).collect(Collectors.toList());
    }

    // Lấy người dùng theo ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_VIEW_USER')")
    public UserDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setFullname(user.getFullname());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setIdentityNumber(user.getIdentityNumber());
            userDTO.setAge(user.getAge());
            userDTO.setBirthday(user.getBirthday());
            userDTO.setAddress(user.getAddress());
            return userDTO;
        }
        return null;
    }

    // Cập nhật người dùng
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_UPDATE_USER')")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        // Chuyển UserDTO thành User
        User user = new User();
        user.setId(id);
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getFullname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setIdentityNumber(userDTO.getIdentityNumber());
        user.setAge(userDTO.getAge());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());

        // Cập nhật thông tin người dùng
        User updatedUser = userService.updateUser(id, user);

        // Chuyển User thành UserDTO để trả về
        UserDTO response = new UserDTO();
        response.setId(updatedUser.getId());
        response.setUsername(updatedUser.getUsername());
        response.setFullname(updatedUser.getFullname());
        response.setPhoneNumber(updatedUser.getPhoneNumber());
        response.setIdentityNumber(updatedUser.getIdentityNumber());
        response.setAge(updatedUser.getAge());
        response.setBirthday(updatedUser.getBirthday());
        response.setAddress(updatedUser.getAddress());

        return response;
    }

    // Xóa người dùng
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DELETE_USER')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
