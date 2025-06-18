package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.PostRequest;
import com.example.quanlysach.dto.response.PostResponse;
import com.example.quanlysach.service.post.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library/post")
public class PostController {

    @Autowired
    private PostService postService;

    // Danh sách tất cả bài viết
    @GetMapping
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<List<PostResponse>> getAllPosts(HttpServletRequest request) {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // Xem chi tiết bài viết
    @GetMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id,
                                                    HttpServletRequest request) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // Thêm bài viết mới
    @PostMapping
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest requestBody,
                                                   HttpServletRequest request) {
        return ResponseEntity.ok(postService.createPost(requestBody));
    }

    // Cập nhật bài viết
    @PutMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id,
                                                   @RequestBody PostRequest requestBody,
                                                   HttpServletRequest request) {
        return ResponseEntity.ok(postService.updatePost(id, requestBody));
    }

    // Xóa bài viết
    @DeleteMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           HttpServletRequest request) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // Like / Dislike bài viết (không yêu cầu phân quyền)
    @PostMapping("/{id}/like")
    public ResponseEntity<PostResponse> likePost(@PathVariable Long id,
                                                 @RequestParam boolean like) {
        return ResponseEntity.ok(postService.likePost(id, like));
    }
}