package com.example.quanlysach.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String fullname;
    private String phoneNumber;
    private String identityNumber;
    private Integer age;
    private LocalDate birthday;
    private String address;
}
