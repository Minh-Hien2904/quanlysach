package com.example.quanlysach.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank(message = "Mã thể loại không được để trống")
    @Size(max = 50, message = "Mã thể loại không được vượt quá 50 ký tự")
    private String code;

    @NotBlank(message = "Tên thể loại không được để trống")
    @Size(max = 100, message = "Tên thể loại không được vượt quá 100 ký tự")
    private String name;
}
