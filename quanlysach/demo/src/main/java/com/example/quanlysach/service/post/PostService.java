package com.example.quanlysach.service.post;

import com.example.quanlysach.dto.request.PostRequest;
import com.example.quanlysach.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getAllPosts();
    PostResponse getPostById(Long id);
    PostResponse createPost(PostRequest request);
    PostResponse updatePost(Long id, PostRequest request);
    void deletePost(Long id);
    PostResponse likePost(Long id, boolean like);
}
