package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.user.UserDTO;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.dto.user.UserRequest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UserMapper {

    // Chuyển từ UserRequest (request từ client) sang User entity
    public static User toEntity(UserRequest request) {
        if (request == null) return null;

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullname(request.getFullname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setIdentityNumber(request.getIdentityNumber());
        user.setAge(request.getAge());
        user.setAddress(request.getAddress());

        // birthday là LocalDate trong UserRequest, lưu sang String yyyy-MM-dd
        if (request.getBirthday() != null) {
            user.setBirthday(request.getBirthday().toString());
        } else {
            user.setBirthday(null);
        }

        // password chưa encode, encode trong service
        user.setPassword(request.getPassword());

        return user;
    }

    // Chuyển từ User entity sang UserDTO trả về client
    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullname(user.getFullname());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setIdentityNumber(user.getIdentityNumber());
        dto.setAge(user.getAge());
        dto.setAddress(user.getAddress());

        // Chuyển birthday String sang LocalDate, xử lý lỗi parse
        if (user.getBirthday() != null && !user.getBirthday().isEmpty()) {
            try {
                dto.setBirthday(LocalDate.parse(user.getBirthday()));
            } catch (DateTimeParseException e) {
                dto.setBirthday(null);
            }
        } else {
            dto.setBirthday(null);
        }

        return dto;
    }
}
