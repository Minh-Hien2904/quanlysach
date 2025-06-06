package com.example.quanlysach.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // === Các trạng thái mở rộng ===

    // Bình luận đã được duyệt hay chưa (dành cho hệ thống cần duyệt trước khi hiển thị)
    private boolean approved;

    // Bình luận đang được hiển thị hay không (ẩn/hiện bởi admin)
    private boolean visible = true;

    // Bình luận đã bị người dùng khác báo cáo vi phạm
    private boolean reported;

    // Đánh dấu đã bị xóa mềm (không xóa khỏi DB nhưng ẩn khỏi người dùng)
    private boolean deleted;

    // Bình luận được ghim lên đầu
    private boolean pinned;
}
