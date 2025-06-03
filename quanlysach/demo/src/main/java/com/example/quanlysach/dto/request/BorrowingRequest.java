package com.example.quanlysach.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingRequest {
    private Long userId;        // id người dùng mượn sách
    private Long bookId;        // id sách
    private LocalDate borrowDate;   // ngày mượn
    private LocalDate dueDate;      // hạn trả (có thể null)
}
