package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.UserResponse;
import com.example.quanlysach.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UserMapper {

    /**
     * Chuyển từ UserRequest (client gửi lên) sang User entity
     */
    public static User toEntity(UserRequest request) {
        if (request == null) return null;

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullname(request.getFullname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setIdentityNumber(request.getIdentityNumber());
        user.setAge(request.getAge());
        user.setAddress(request.getAddress());

        if (request.getBirthday() != null) {
            user.setBirthday(request.getBirthday().toString());
        } else {
            user.setBirthday(null);
        }

        user.setPassword(request.getPassword()); // sẽ mã hóa ở service
        return user;
    }

    /**
     * Chuyển từ User entity sang UserResponse (trả về client)
     */
    public static UserResponse toResponse(User user) {
        if (user == null) return null;

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFullname(user.getFullname());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setIdentityNumber(user.getIdentityNumber());
        response.setAge(user.getAge());
        response.setAddress(user.getAddress());

        if (user.getBirthday() != null && !user.getBirthday().isEmpty()) {
            try {
                response.setBirthday(LocalDate.parse(user.getBirthday()));
            } catch (DateTimeParseException e) {
                response.setBirthday(null);
            }
        } else {
            response.setBirthday(null);
        }

        return response;
    }
}
