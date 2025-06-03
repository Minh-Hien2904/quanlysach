package com.example.quanlysach.dto.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Long postId;
}