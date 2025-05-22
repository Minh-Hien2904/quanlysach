package com.example.quanlysach.dto.book;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private LocalDate publishedDate;
}

