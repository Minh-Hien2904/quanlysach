package com.example.quanlysach.service.post;

import com.example.quanlysach.dto.post.PostRequestDTO;
import com.example.quanlysach.dto.post.PostResponseDTO;

import java.util.List;

public interface PostService {
    List<PostResponseDTO> getAllPosts();
    PostResponseDTO getPostById(Long id);
    PostResponseDTO createPost(PostRequestDTO request);
    PostResponseDTO updatePost(Long id, PostRequestDTO request);
    void deletePost(Long id);
    PostResponseDTO likePost(Long id, boolean like);
}
