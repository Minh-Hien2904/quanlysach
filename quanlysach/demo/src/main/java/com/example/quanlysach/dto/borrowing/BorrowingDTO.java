package com.example.quanlysach.dto.borrowing;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BorrowingDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long bookId;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;
}
