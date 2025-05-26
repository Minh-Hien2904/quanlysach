package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.borrowing.BorrowingDTO;
import com.example.quanlysach.entity.Borrowing;

public class BorrowingMapper {

    public static BorrowingDTO toDTO(Borrowing borrowing) {
        BorrowingDTO dto = new BorrowingDTO();
        dto.setId(borrowing.getId());
        dto.setUserId(borrowing.getUser().getId());
        dto.setUserName(borrowing.getUser().getName());
        dto.setBookId(borrowing.getBook().getId());
        dto.setBookTitle(borrowing.getBook().getTitle());
        dto.setBorrowDate(borrowing.getBorrowDate());
        dto.setDueDate(borrowing.getDueDate());
        dto.setReturnDate(borrowing.getReturnDate());
        dto.setReturned(borrowing.isReturned());
        return dto;
    }
}
