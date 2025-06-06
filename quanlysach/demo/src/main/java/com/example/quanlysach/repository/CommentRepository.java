package com.example.quanlysach.repository;

import com.example.quanlysach.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    List<Comment> findByReportedTrue();

    // Thêm các phương thức count
    long countByApprovedTrue();
    long countByReportedTrue();
    long countByDeletedTrue();
}
