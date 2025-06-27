package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.UserResponse;
import com.example.quanlysach.entity.User;

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
        user.setBirthday(request.getBirthday()); // ✅ Không cần .toString()
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
        response.setBirthday(user.getBirthday()); // ✅ Không cần parse
        return response;
    }
}
