package com.example.quanlysach.repository;

import com.example.quanlysach.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}