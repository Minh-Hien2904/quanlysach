package com.example.quanlysach.controller;

import com.example.quanlysach.dto.response.BorrowingResponse;
import com.example.quanlysach.service.borrowing.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        try {
            BorrowingResponse response = borrowingService.borrowBook(userId, bookId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam Long borrowingId) {
        try {
            BorrowingResponse response = borrowingService.returnBook(borrowingId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/unreturned/{userId}")
    public ResponseEntity<?> getUnreturnedBorrowings(@PathVariable Long userId) {
        List<BorrowingResponse> responses = borrowingService.getUnreturnedBorrowingsByUser(userId);
        return ResponseEntity.ok(responses);
    }
}
