package com.example.quanlysach.controller;

import com.example.quanlysach.entity.Borrowing;
import com.example.quanlysach.service.borrowing.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        try {
            Borrowing borrowing = borrowingService.borrowBook(userId, bookId);
            return ResponseEntity.ok(borrowing);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam Long borrowingId) {
        try {
            Borrowing borrowing = borrowingService.returnBook(borrowingId);
            return ResponseEntity.ok(borrowing);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

