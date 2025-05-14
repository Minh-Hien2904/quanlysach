package com.example.quanlysach.service;

import com.example.quanlysach.dto.UserDTO;
import com.example.quanlysach.model.User;
import com.example.quanlysach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO registerUser(UserDTO userDTO) {
        // Chuyển UserDTO thành User
        User user = convertToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // Mã hóa mật khẩu
        User savedUser = userRepository.save(user);

        // Chuyển User thành UserDTO để trả về
        return convertToUserDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToUserDTO).orElse(null);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setFullname(userDTO.getFullname());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setIdentityNumber(userDTO.getIdentityNumber());
            user.setAge(userDTO.getAge());
            // Chuyển đổi ngày sinh từ String sang LocalDate
            user.setBirthday(userDTO.getBirthday().toString());
            user.setAddress(userDTO.getAddress());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // Mã hóa mật khẩu khi cập nhật
            User updatedUser = userRepository.save(user);
            return convertToUserDTO(updatedUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getFullname());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setIdentityNumber(user.getIdentityNumber());
        userDTO.setAge(user.getAge());
        // Chuyển đổi birthday từ String sang LocalDate
        userDTO.setBirthday(LocalDate.parse(user.getBirthday()));
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getFullname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setIdentityNumber(userDTO.getIdentityNumber());
        user.setAge(userDTO.getAge());
        // Chuyển đổi birthday từ LocalDate sang String
        user.setBirthday(userDTO.getBirthday().toString());
        user.setAddress(userDTO.getAddress());
        return user;
    }
}
