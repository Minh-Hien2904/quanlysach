package com.example.quanlysach.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookResponse {
    private Long id;
    private String code;
    private String title;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
