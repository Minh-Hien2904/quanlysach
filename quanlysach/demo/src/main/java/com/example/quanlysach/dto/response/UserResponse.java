package com.example.quanlysach.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String fullname;
    private String phoneNumber;
    private String identityNumber;
    private Integer age;
    private LocalDate birthday;
    private String address;
}
