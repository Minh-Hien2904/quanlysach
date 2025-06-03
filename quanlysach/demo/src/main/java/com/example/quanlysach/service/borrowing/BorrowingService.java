package com.example.quanlysach.service.borrowing;

import com.example.quanlysach.dto.response.BorrowingResponse;

import java.util.List;

public interface BorrowingService {
    List<BorrowingResponse> getUnreturnedBorrowingsByUser(Long userId);
    BorrowingResponse findUnreturnedBorrowing(Long userId, Long bookId);
    BorrowingResponse borrowBook(Long userId, Long bookId);
    BorrowingResponse returnBook(Long borrowingId);
}
