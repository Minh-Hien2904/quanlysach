package com.example.quanlysach.repository;

import com.example.quanlysach.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}