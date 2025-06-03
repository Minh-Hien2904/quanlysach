package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.request.PostRequest;
import com.example.quanlysach.dto.response.PostResponse;
import com.example.quanlysach.entity.Post;

public class PostMapper {

    public static PostResponse toDTO(Post post) {
        PostResponse dto = new PostResponse();
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

    public static Post toEntity(PostRequest dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setPublisher(dto.getPublisher());
        return post;
    }

    public static void updateEntity(Post post, PostRequest dto) {
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setPublisher(dto.getPublisher());
    }
}