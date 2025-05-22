package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.book.*;
import com.example.quanlysach.entity.Book;

import java.time.LocalDate;

public class BookMapper {

    // Entity -> BookDTO (cơ bản)
    public static BookDTO toBookDTO(Book book) {
        if (book == null) return null;
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        // Chuyển publishYear -> publishedDate lấy ngày 1 tháng 1 năm publishYear
        if (book.getPublishYear() != null) {
            dto.setPublishedDate(LocalDate.of(book.getPublishYear(), 1, 1));
        }
        return dto;
    }

    // Entity -> BookSummaryDTO
    public static BookSummaryDTO toSummaryDTO(Book book) {
        if (book == null) return null;
        BookSummaryDTO dto = new BookSummaryDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        if (book.getPublishYear() != null) {
            dto.setPublishedDate(LocalDate.of(book.getPublishYear(), 1, 1));
        }
        return dto;
    }

    // Entity -> BookDetailDTO
    // NOTE: Vì nhiều trường không có trong entity (description, pageCount,...),
    // ta chỉ map những trường có thể từ entity
    public static BookDetailDTO toDetailDTO(Book book) {
        if (book == null) return null;
        BookDetailDTO dto = new BookDetailDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        if (book.getPublishYear() != null) {
            dto.setPublishedDate(LocalDate.of(book.getPublishYear(), 1, 1));
        }
        // Không map description, pageCount, categoryName, available, borrowCount vì entity không có
        return dto;
    }

    // BookCreateDTO -> Entity
    public static Book toEntity(BookCreateDTO dto) {
        if (dto == null) return null;
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        if (dto.getPublishedDate() != null) {
            book.setPublishYear(dto.getPublishedDate().getYear());
        }
        // Category và status chưa có trong dto, nên set mặc định hoặc để null
        book.setCategory(null);
        book.setStatus(null);
        return book;
    }

    // BookUpdateDTO + Entity -> Entity
    public static void updateEntity(BookUpdateDTO dto, Book book) {
        if (dto == null || book == null) return;
        if (dto.getTitle() != null) book.setTitle(dto.getTitle());
        if (dto.getAuthor() != null) book.setAuthor(dto.getAuthor());
        if (dto.getPublishedDate() != null) book.setPublishYear(dto.getPublishedDate().getYear());
    }
}
