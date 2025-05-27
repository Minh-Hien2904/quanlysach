package com.example.quanlysach.controller;

import com.example.quanlysach.dto.post.PostRequestDTO;
import com.example.quanlysach.dto.post.PostResponseDTO;
import com.example.quanlysach.service.post.PostService;
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
    @PreAuthorize("hasAuthority('ROLE_VIEW_POST')")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // Xem chi tiết bài viết
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_VIEW_POST')")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // Thêm bài viết mới
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_POST')")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    // Cập nhật bài viết
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_POST')")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @RequestBody PostRequestDTO request) {
        return ResponseEntity.ok(postService.updatePost(id, request));
    }

    // Xóa bài viết
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_POST')")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // Like / Dislike bài viết
    @PostMapping("/{id}/like")
    public ResponseEntity<PostResponseDTO> likePost(@PathVariable Long id, @RequestParam boolean like) {
        return ResponseEntity.ok(postService.likePost(id, like));
    }
}
