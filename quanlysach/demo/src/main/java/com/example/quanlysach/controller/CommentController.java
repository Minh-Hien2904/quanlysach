package com.example.quanlysach.controller;

import com.example.quanlysach.dto.comment.CommentRequest;
import com.example.quanlysach.dto.comment.CommentResponse;
import com.example.quanlysach.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/library/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_VIEW_COMMENT')")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@RequestParam Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_VIEW_COMMENT')")
    public ResponseEntity<CommentResponse> getCommentDetail(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentDetail(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_CREATE_COMMENT')")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest request, Principal principal) {
        return ResponseEntity.ok(commentService.createComment(request, principal.getName()));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_COMMENT')")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id, @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_COMMENT')")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
