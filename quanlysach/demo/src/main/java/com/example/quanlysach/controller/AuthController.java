package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.LoginRequest;
import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.LoginResponse;
import com.example.quanlysach.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }
}