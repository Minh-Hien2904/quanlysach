package com.example.quanlysach.controller;

import com.example.quanlysach.entity.User;
import com.example.quanlysach.repository.UserRepository;
import com.example.quanlysach.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepo;
    @Autowired private JwtService jwtService;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userRepo.findByUsername(username).orElseThrow();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name()) // assuming Enum
                .toList();

        return jwtService.generateToken(username, roles);
    }
}
