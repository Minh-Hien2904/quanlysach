package com.example.quanlysach.service.user;

import com.example.quanlysach.dto.request.UserRequest;
import com.example.quanlysach.dto.response.UserResponse;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerUser(UserRequest request) {
        User user = convertToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return convertToResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToResponse).orElse(null);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(request.getUsername());
            user.setFullname(request.getFullname());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setIdentityNumber(request.getIdentityNumber());
            user.setAge(request.getAge() != null ? request.getAge() : 0);
            user.setBirthday(request.getBirthday() != null ? request.getBirthday().toString() : null);
            user.setAddress(request.getAddress());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            User updatedUser = userRepository.save(user);
            return convertToResponse(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Convert UserRequest -> User
    private User convertToEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullname(request.getFullname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setIdentityNumber(request.getIdentityNumber());
        user.setAge(request.getAge() != null ? request.getAge() : 0);
        user.setBirthday(request.getBirthday() != null ? request.getBirthday().toString() : null);
        user.setAddress(request.getAddress());
        return user;
    }

    // Convert User -> UserResponse
    private UserResponse convertToResponse(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullname(user.getFullname());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setIdentityNumber(user.getIdentityNumber());
        dto.setAge(user.getAge());
        if (user.getBirthday() != null && !user.getBirthday().isEmpty()) {
            try {
                dto.setBirthday(LocalDate.parse(user.getBirthday()));
            } catch (DateTimeParseException e) {
                dto.setBirthday(null);
            }
        }
        dto.setAddress(user.getAddress());
        return dto;
    }
}
