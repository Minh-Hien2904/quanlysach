package com.example.quanlysach.service.auth;

import com.example.quanlysach.dto.request.LoginRequest;
import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    void register(UserRequest request);
}