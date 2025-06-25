package com.example.quanlysach.service.auth;

import com.example.quanlysach.dto.request.LoginRequest;
import com.example.quanlysach.dto.response.LoginResponse;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.repository.UserRepository;
import com.example.quanlysach.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired private UserRepository userRepo;
    @Autowired private JwtService jwtService;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .toList();

        String token = jwtService.generateToken(request.getUsername(), roles);
        return new LoginResponse(token, "Bearer", request.getUsername(), roles);
    }
}