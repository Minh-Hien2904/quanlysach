package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.response.CommentResponse;
import com.example.quanlysach.dto.request.CommentRequest;
import com.example.quanlysach.entity.Comment;
import com.example.quanlysach.entity.Post;
import com.example.quanlysach.entity.User;

import java.time.LocalDateTime;

public class CommentMapper {

    public static CommentResponse toDto(Comment comment) {
        CommentResponse dto = new CommentResponse();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUsername(comment.getUser().getUsername());
        return dto;
    }

    public static Comment toEntity(CommentRequest request, Post post, User user) {
        return Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
