package com.example.quanlysach.service.impl;

import com.example.quanlysach.dto.user.UserDTO;
import com.example.quanlysach.dto.user.UserRequest;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.repository.UserRepository;
import com.example.quanlysach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserRequest request) {
        User user = convertToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Mã hóa password
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public UserDTO updateUser(Long id, UserRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(request.getUsername());
            user.setFullname(request.getFullname());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setIdentityNumber(request.getIdentityNumber());
            user.setAge(request.getAge());
            user.setBirthday(request.getBirthday().toString());
            user.setAddress(request.getAddress());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            User updatedUser = userRepository.save(user);
            return convertToDTO(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Mapping methods

    private User convertToEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullname(request.getFullname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setIdentityNumber(request.getIdentityNumber());
        user.setAge(request.getAge());
        user.setBirthday(request.getBirthday().toString());
        user.setAddress(request.getAddress());
        return user;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullname(user.getFullname());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setIdentityNumber(user.getIdentityNumber());
        dto.setAge(user.getAge());
        dto.setBirthday(LocalDate.parse(user.getBirthday()));
        dto.setAddress(user.getAddress());
        return dto;
    }
}
