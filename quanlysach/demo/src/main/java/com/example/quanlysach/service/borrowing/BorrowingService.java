package com.example.quanlysach.service.borrowing;

import com.example.quanlysach.entity.Borrowing;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BorrowingService {
    List<Borrowing> getUnreturnedBorrowingsByUser(User user);
    Optional<Borrowing> findUnreturnedBorrowing(User user, Book book);
    Borrowing saveBorrowing(Borrowing borrowing);
    Borrowing borrowBook(Long userId, Long bookId); // thêm
    Borrowing returnBook(Long borrowingId); // thêm
}
