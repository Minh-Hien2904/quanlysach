package com.example.quanlysach.service.comment;

import com.example.quanlysach.dto.response.CommentResponse;
import com.example.quanlysach.dto.request.CommentRequest;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getCommentsByPost(Long postId);
    CommentResponse getCommentDetail(Long id);
    CommentResponse createComment(CommentRequest request, String username);
    CommentResponse updateComment(Long id, CommentRequest request);
    void deleteComment(Long id);
}
