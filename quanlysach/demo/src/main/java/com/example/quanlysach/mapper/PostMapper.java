package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.post.PostRequestDTO;
import com.example.quanlysach.dto.post.PostResponseDTO;
import com.example.quanlysach.entity.Post;

public class PostMapper {

    public static PostResponseDTO toDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setPublisher(post.getPublisher());
        dto.setLikes(post.getLikes());
        dto.setDislikes(post.getDislikes());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }

    public static Post toEntity(PostRequestDTO dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setPublisher(dto.getPublisher());
        return post;
    }

    public static void updateEntity(Post post, PostRequestDTO dto) {
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setPublisher(dto.getPublisher());
    }
}
