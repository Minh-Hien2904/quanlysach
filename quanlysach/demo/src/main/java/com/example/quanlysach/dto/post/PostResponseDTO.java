package com.example.quanlysach.dto.post;

import java.time.LocalDateTime;

public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String publisher;
    private int likes;
    private int dislikes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
