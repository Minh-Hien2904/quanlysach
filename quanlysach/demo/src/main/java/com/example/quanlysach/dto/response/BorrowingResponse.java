package com.example.quanlysach.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingResponse {
    private Long id;
    private Long userId;
    private String userName;
    private Long bookId;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;  // null nếu chưa trả
    private boolean returned;      // trạng thái đã trả hay chưa
}