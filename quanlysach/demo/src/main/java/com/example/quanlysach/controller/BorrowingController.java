package com.example.quanlysach.controller;

import com.example.quanlysach.dto.response.BorrowingResponse;
import com.example.quanlysach.service.borrowing.BorrowingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    // Mượn sách.
    @PreAuthorize("fileRole(#request)")
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestParam Long userId,
                                        @RequestParam Long bookId,
                                        HttpServletRequest request) {
        try {
            BorrowingResponse response = borrowingService.borrowBook(userId, bookId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã xảy ra lỗi khi mượn sách.");
        }
    }

    // Trả sách.
    @PreAuthorize("fileRole(#request)")
    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam Long borrowingId,
                                        HttpServletRequest request) {
        try {
            BorrowingResponse response = borrowingService.returnBook(borrowingId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã xảy ra lỗi khi trả sách.");
        }
    }

    // Xem danh sách sách chưa trả của một người dùng.
    @PreAuthorize("fileRole(#request)")
    @GetMapping("/unreturned/{userId}")
    public ResponseEntity<?> getUnreturnedBorrowings(@PathVariable Long userId, HttpServletRequest request) {
        try {
            List<BorrowingResponse> responses = borrowingService.getUnreturnedBorrowingsByUser(userId);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Không thể lấy danh sách sách chưa trả.");
        }
    }
}