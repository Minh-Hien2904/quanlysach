package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.CommentRequest;
import com.example.quanlysach.dto.response.CommentResponse;
import com.example.quanlysach.service.comment.CommentService;
import jakarta.servlet.http.HttpServletRequest;
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
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@RequestParam Long postId,
                                                                   @RequestParam(required = false, defaultValue = "false") boolean includeHidden,
                                                                   HttpServletRequest request) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId, includeHidden));
    }

    // Người dùng & Admin - Xem chi tiết bình luận
    @GetMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<CommentResponse> getCommentDetail(@PathVariable Long id,
                                                            HttpServletRequest request) {
        return ResponseEntity.ok(commentService.getCommentDetail(id));
    }

    // Người dùng - Tạo bình luận
    @PostMapping("/create")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest requestBody,
                                                         Principal principal,
                                                         HttpServletRequest request) {
        return ResponseEntity.ok(commentService.createComment(requestBody, principal.getName()));
    }

    // Người dùng - Cập nhật bình luận của mình
    @PutMapping("/update/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<CommentResponse> updateOwnComment(@PathVariable Long id,
                                                            @RequestBody CommentRequest requestBody,
                                                            Principal principal,
                                                            HttpServletRequest request) {
        return ResponseEntity.ok(commentService.updateOwnComment(id, requestBody, principal.getName()));
    }

    // Admin - Cập nhật bình luận bất kỳ
    @PutMapping("/admin/update/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<CommentResponse> updateCommentByAdmin(@PathVariable Long id,
                                                                @RequestBody CommentRequest requestBody,
                                                                HttpServletRequest request) {
        return ResponseEntity.ok(commentService.updateCommentByAdmin(id, requestBody));
    }

    // Người dùng - Xóa bình luận của mình
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> deleteOwnComment(@PathVariable Long id,
                                              Principal principal,
                                              HttpServletRequest request) {
        commentService.deleteOwnComment(id, principal.getName());
        return ResponseEntity.ok().build();
    }

    // Admin - Xóa bất kỳ bình luận nào
    @DeleteMapping("/admin/delete/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> deleteAnyComment(@PathVariable Long id,
                                              HttpServletRequest request) {
        commentService.deleteAnyComment(id);
        return ResponseEntity.ok().build();
    }

    // Admin - Ẩn/hiện bình luận
    @PutMapping("/admin/toggle-visibility/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> toggleCommentVisibility(@PathVariable Long id,
                                                     HttpServletRequest request) {
        commentService.toggleCommentVisibility(id);
        return ResponseEntity.ok().build();
    }

    // Admin - Duyệt bình luận
    @PutMapping("/admin/approve/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> approveComment(@PathVariable Long id,
                                            HttpServletRequest request) {
        commentService.approveComment(id);
        return ResponseEntity.ok().build();
    }

    // Người dùng - Báo cáo bình luận
    @PostMapping("/report/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> reportComment(@PathVariable Long id,
                                           @RequestBody String reason,
                                           Principal principal,
                                           HttpServletRequest request) {
        commentService.reportComment(id, reason, principal.getName());
        return ResponseEntity.ok().build();
    }

    // Admin - Xem danh sách bình luận bị báo cáo
    @GetMapping("/admin/reported")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<List<CommentResponse>> getReportedComments(HttpServletRequest request) {
        return ResponseEntity.ok(commentService.getReportedComments());
    }

    // Admin - Cấm người dùng bình luận
    @PutMapping("/admin/ban-user")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> banUserFromCommenting(@RequestParam String username,
                                                   HttpServletRequest request) {
        commentService.banUserFromCommenting(username);
        return ResponseEntity.ok().build();
    }

    // Admin - Ghim bình luận
    @PutMapping("/admin/pin/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> pinComment(@PathVariable Long id,
                                        HttpServletRequest request) {
        commentService.pinComment(id);
        return ResponseEntity.ok().build();
    }

    // Admin - Thống kê/truy vết bình luận
    @GetMapping("/admin/statistics")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> getCommentStatistics(HttpServletRequest request) {
        return ResponseEntity.ok(commentService.getCommentStatistics());
    }
}
