package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.CommentRequest;
import com.example.quanlysach.dto.response.CommentResponse;
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

    // Người dùng & Admin - Xem bình luận theo post
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_VIEW_COMMENT', 'ROLE_ADMIN_VIEW_ALL_COMMENTS')")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@RequestParam Long postId,
                                                                   @RequestParam(required = false, defaultValue = "false") boolean includeHidden) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId, includeHidden));
    }

    // Người dùng & Admin - Xem chi tiết bình luận
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_VIEW_COMMENT', 'ROLE_ADMIN_VIEW_ALL_COMMENTS')")
    public ResponseEntity<CommentResponse> getCommentDetail(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentDetail(id));
    }

    // Người dùng - Tạo bình luận
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_CREATE_COMMENT')")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest request, Principal principal) {
        return ResponseEntity.ok(commentService.createComment(request, principal.getName()));
    }

    // Người dùng - Cập nhật bình luận của mình
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_OWN_COMMENT')")
    public ResponseEntity<CommentResponse> updateOwnComment(@PathVariable Long id, @RequestBody CommentRequest request, Principal principal) {
        return ResponseEntity.ok(commentService.updateOwnComment(id, request, principal.getName()));
    }

    // Admin - Cập nhật bình luận bất kỳ (nếu được phép)
    @PutMapping("/admin/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_UPDATE_COMMENT')")
    public ResponseEntity<CommentResponse> updateCommentByAdmin(@PathVariable Long id, @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.updateCommentByAdmin(id, request));
    }

    // Người dùng - Xóa bình luận của mình
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_OWN_COMMENT')")
    public ResponseEntity<?> deleteOwnComment(@PathVariable Long id, Principal principal) {
        commentService.deleteOwnComment(id, principal.getName());
        return ResponseEntity.ok().build();
    }

    // Admin - Xóa bất kỳ bình luận nào
    @DeleteMapping("/admin/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_DELETE_COMMENT')")
    public ResponseEntity<?> deleteAnyComment(@PathVariable Long id) {
        commentService.deleteAnyComment(id);
        return ResponseEntity.ok().build();
    }

    // Admin - Ẩn/hiện bình luận
    @PutMapping("/admin/toggle-visibility/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_TOGGLE_COMMENT_VISIBILITY')")
    public ResponseEntity<?> toggleCommentVisibility(@PathVariable Long id) {
        commentService.toggleCommentVisibility(id);
        return ResponseEntity.ok().build();
    }

    // Admin - Duyệt bình luận
    @PutMapping("/admin/approve/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_APPROVE_COMMENT')")
    public ResponseEntity<?> approveComment(@PathVariable Long id) {
        commentService.approveComment(id);
        return ResponseEntity.ok().build();
    }

    // Người dùng - Báo cáo bình luận
    @PostMapping("/report/{id}")
    @PreAuthorize("hasAuthority('ROLE_REPORT_COMMENT')")
    public ResponseEntity<?> reportComment(@PathVariable Long id, @RequestBody String reason, Principal principal) {
        commentService.reportComment(id, reason, principal.getName());
        return ResponseEntity.ok().build();
    }

    // Admin - Xem danh sách bình luận bị báo cáo
    @GetMapping("/admin/reported")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_VIEW_REPORTED_COMMENTS')")
    public ResponseEntity<List<CommentResponse>> getReportedComments() {
        return ResponseEntity.ok(commentService.getReportedComments());
    }

    // Admin - Cấm người dùng bình luận
    @PutMapping("/admin/ban-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_BAN_USER_COMMENT')")
    public ResponseEntity<?> banUserFromCommenting(@RequestParam String username) {
        commentService.banUserFromCommenting(username);
        return ResponseEntity.ok().build();
    }

    // Admin - Ghim bình luận
    @PutMapping("/admin/pin/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_PIN_COMMENT')")
    public ResponseEntity<?> pinComment(@PathVariable Long id) {
        commentService.pinComment(id);
        return ResponseEntity.ok().build();
    }

    // Admin - Thống kê/truy vết bình luận
    @GetMapping("/admin/statistics")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_COMMENT_STATISTICS')")
    public ResponseEntity<?> getCommentStatistics() {
        return ResponseEntity.ok(commentService.getCommentStatistics());
    }
}
