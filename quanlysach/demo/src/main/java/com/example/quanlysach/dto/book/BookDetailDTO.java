package com.example.quanlysach.dto.book;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookDetailDTO {
    private Long id;
    private String title;
    private String author;
    private String description;
    private int pageCount;
    private LocalDate publishedDate;
    private String categoryName;
    private boolean available;
    private int borrowCount;
}

