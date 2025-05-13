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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Phương thức chuyển User sang UserDTO
    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getFullname());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setIdentityNumber(user.getIdentityNumber());
        userDTO.setAge(user.getAge());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }

    // Phương thức để chuyển UserDTO sang User (nếu cần khi lưu dữ liệu)
    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getFullname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setIdentityNumber(userDTO.getIdentityNumber());
        user.setAge(userDTO.getAge());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());
        return user;
    }

    // Đăng ký người dùng mới (trả về UserDTO)
    public UserDTO registerUser(UserDTO userDTO) {
        User user = convertToUser(userDTO);  // Chuyển UserDTO sang User
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Mã hóa mật khẩu trước khi lưu
        user = userRepository.save(user);
        return convertToUserDTO(user);  // Chuyển User sang UserDTO để trả về
    }

    // Lấy tất cả người dùng (trả về danh sách UserDTO)
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();  // Lấy danh sách người dùng
        return users.stream()
                .map(this::convertToUserDTO)  // Chuyển User sang UserDTO
                .collect(Collectors.toList());
    }

    // Lấy người dùng theo ID (trả về UserDTO)
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);  // Lấy người dùng từ database
        return user != null ? convertToUserDTO(user) : null;  // Trả về UserDTO nếu tìm thấy, ngược lại trả null
    }

    // Cập nhật thông tin người dùng (trả về UserDTO)
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setFullname(userDTO.getFullname());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setIdentityNumber(userDTO.getIdentityNumber());
            user.setAge(userDTO.getAge());
            user.setBirthday(userDTO.getBirthday());
            user.setAddress(userDTO.getAddress());
            user = userRepository.save(user);  // Lưu lại người dùng đã cập nhật
            return convertToUserDTO(user);  // Trả về UserDTO sau khi cập nhật
        }
        return null;  // Trả về null nếu không tìm thấy người dùng
    }

    // Xóa người dùng
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
