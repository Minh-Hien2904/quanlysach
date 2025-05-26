package com.example.quanlysach.repository;

import com.example.quanlysach.entity.Comment;
import com.example.quanlysach.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
