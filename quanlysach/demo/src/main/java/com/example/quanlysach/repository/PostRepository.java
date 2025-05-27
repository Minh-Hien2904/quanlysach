package com.example.quanlysach.repository;

import com.example.quanlysach.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

