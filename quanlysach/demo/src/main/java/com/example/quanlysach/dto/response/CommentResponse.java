package com.example.quanlysach.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;

    private boolean visible;
    private boolean approved;
    private boolean reported;
    private boolean pinned;
}