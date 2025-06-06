package com.example.quanlysach.service.comment;

import com.example.quanlysach.dto.request.CommentRequest;
import com.example.quanlysach.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getCommentsByPost(Long postId);
    List<CommentResponse> getCommentsByPost(Long postId, boolean includeHidden);
    CommentResponse getCommentDetail(Long id);
    CommentResponse createComment(CommentRequest request, String username);
    CommentResponse updateComment(Long id, CommentRequest request);

    // Các phương thức mới cho phân quyền chi tiết
    CommentResponse updateOwnComment(Long id, CommentRequest request, String username);
    CommentResponse updateCommentByAdmin(Long id, CommentRequest request);
    void deleteOwnComment(Long id, String username);
    void deleteAnyComment(Long id);
    CommentResponse toggleCommentVisibility(Long id);
    CommentResponse approveComment(Long id);
    CommentResponse pinComment(Long id);
    CommentResponse reportComment(Long id, String reason, String username);
    List<CommentResponse> getReportedComments();
    void banUserFromCommenting(String username);
    Object getCommentStatistics();
}
