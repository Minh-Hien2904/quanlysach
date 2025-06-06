package com.example.quanlysach.service.comment;

import com.example.quanlysach.dto.request.CommentRequest;
import com.example.quanlysach.dto.response.CommentResponse;
import com.example.quanlysach.entity.Comment;
import com.example.quanlysach.repository.CommentRepository;
import com.example.quanlysach.repository.PostRepository;
import com.example.quanlysach.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 1. Implement method getCommentsByPost(Long postId)
    @Override
    public List<CommentResponse> getCommentsByPost(Long postId) {
        // Lấy comment chỉ visible và chưa bị xóa
        return commentRepository.findByPostId(postId)
                .stream()
                .filter(c -> c.isVisible() && !c.isDeleted())
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 2. Implement method getCommentsByPost(Long postId, boolean includeHidden)
    @Override
    public List<CommentResponse> getCommentsByPost(Long postId, boolean includeHidden) {
        return commentRepository.findByPostId(postId)
                .stream()
                .filter(c -> includeHidden || (c.isVisible() && !c.isDeleted()))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse getCommentDetail(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return toDto(comment);
    }

    @Override
    public CommentResponse createComment(CommentRequest request, String username) {
        var post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        var comment = Comment.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .post(post)
                .user(user)
                .visible(true)
                .approved(false)
                .deleted(false)
                .pinned(false)
                .reported(false)
                .build();

        return toDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponse updateComment(Long id, CommentRequest request) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        return toDto(commentRepository.save(comment));
    }

    // Implement method updateOwnComment(Long id, CommentRequest request, String username)
    @Override
    public CommentResponse updateOwnComment(Long id, CommentRequest request, String username) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        if (!comment.getUser().getUsername().equals(username)) {
            throw new SecurityException("Not authorized to update this comment");
        }
        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        return toDto(commentRepository.save(comment));
    }

    // Implement method updateCommentByAdmin(Long id, CommentRequest request)
    @Override
    public CommentResponse updateCommentByAdmin(Long id, CommentRequest request) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        return toDto(commentRepository.save(comment));
    }

    // Implement method deleteOwnComment(Long id, String username)
    @Override
    public void deleteOwnComment(Long id, String username) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        if (!comment.getUser().getUsername().equals(username)) {
            throw new SecurityException("Not authorized to delete this comment");
        }
        comment.setDeleted(true);
        commentRepository.save(comment);
    }

    // Implement method deleteAnyComment(Long id)
    @Override
    public void deleteAnyComment(Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setDeleted(true);
        commentRepository.save(comment);
    }

    @Override
    public CommentResponse toggleCommentVisibility(Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setVisible(!comment.isVisible());
        return toDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponse approveComment(Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setApproved(true);
        return toDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponse pinComment(Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setPinned(true);
        return toDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponse reportComment(Long id, String reason, String username) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setReported(true);
        // Có thể lưu thêm báo cáo lý do, user báo cáo nếu entity hỗ trợ
        return toDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentResponse> getReportedComments() {
        return commentRepository.findByReportedTrue()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void banUserFromCommenting(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setCommentBanned(true);
        userRepository.save(user);
    }

    @Override
    public Object getCommentStatistics() {
        // Ví dụ: trả về tổng số comment, comment đã duyệt, comment bị report,...
        long total = commentRepository.count();
        long approved = commentRepository.countByApprovedTrue();
        long reported = commentRepository.countByReportedTrue();
        long deleted = commentRepository.countByDeletedTrue();

        return new CommentStatistics(total, approved, reported, deleted);
    }

    // Helper DTO class để trả về thống kê (bạn có thể đặt ở file khác)
    public static class CommentStatistics {
        public long totalComments;
        public long approvedComments;
        public long reportedComments;
        public long deletedComments;

        public CommentStatistics(long total, long approved, long reported, long deleted) {
            this.totalComments = total;
            this.approvedComments = approved;
            this.reportedComments = reported;
            this.deletedComments = deleted;
        }
    }

    // Phương thức chuyển đổi từ entity sang DTO response
    private CommentResponse toDto(Comment comment) {
        var dto = new CommentResponse();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());
        dto.setUsername(comment.getUser().getUsername());
        dto.setVisible(comment.isVisible());
        dto.setApproved(comment.isApproved());
        dto.setReported(comment.isReported());
        dto.setPinned(comment.isPinned());
        return dto;
    }
}
