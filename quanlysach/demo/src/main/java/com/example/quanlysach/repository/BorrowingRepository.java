package com.example.quanlysach.repository;

import com.example.quanlysach.entity.Borrowing;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    List<Borrowing> findByUserAndReturnedFalse(User user);

    Optional<Borrowing> findByUserAndBookAndReturnedFalse(User user, Book book);
}
