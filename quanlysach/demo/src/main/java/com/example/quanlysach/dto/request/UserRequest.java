package com.example.quanlysach.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String address;
}
