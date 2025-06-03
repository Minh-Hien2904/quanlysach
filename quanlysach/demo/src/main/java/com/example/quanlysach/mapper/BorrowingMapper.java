package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.response.BorrowingResponse;
import com.example.quanlysach.entity.Borrowing;
import org.springframework.stereotype.Component;

@Component
public class BorrowingMapper {

    public BorrowingResponse toResponse(Borrowing borrowing) {
        if (borrowing == null) return null;

        BorrowingResponse dto = new BorrowingResponse();

        dto.setId(borrowing.getId());

        if (borrowing.getUser() != null) {
            dto.setUserId(borrowing.getUser().getId());
            dto.setUserName(borrowing.getUser().getFullname()); // hoặc getName() nếu dùng field khác
        }

        if (borrowing.getBook() != null) {
            dto.setBookId(borrowing.getBook().getId());
            dto.setBookTitle(borrowing.getBook().getTitle());
        }

        dto.setBorrowDate(borrowing.getBorrowDate());
        dto.setDueDate(borrowing.getDueDate());
        dto.setReturnDate(borrowing.getReturnDate());
        dto.setReturned(borrowing.isReturned());

        return dto;
    }
}
