package com.example.quanlysach.service.auth;

import com.example.quanlysach.dto.request.LoginRequest;
import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.LoginResponse;
import com.example.quanlysach.entity.ERole;
import com.example.quanlysach.entity.Role;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.repository.RoleRepository;
import com.example.quanlysach.repository.UserRepository;
import com.example.quanlysach.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .toList();

        String token = jwtService.generateToken(
                user.getId(),
                user.getUsername(),
                user.getEmail(),        // Đảm bảo entity User có getEmail()
                user.getFullname(),
                roles
        );

        return new LoginResponse(
                token,
                "Bearer",
                user.getId(),
                user.getUsername(),
                user.getFullname(),
                user.getEmail(),
                roles
        );
    }

    public void register(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username đã tồn tại");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullname(request.getFullname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setIdentityNumber(request.getIdentityNumber());
        user.setAge(request.getAge());
        user.setBirthday(request.getBirthday());        // yyyy-MM-dd
        user.setAddress(request.getAddress());

        // Gán role mặc định
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ROLE_USER"));
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);
    }
}
