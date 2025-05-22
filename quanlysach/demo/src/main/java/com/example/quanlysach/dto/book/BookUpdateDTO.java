package com.example.quanlysach.dto.book;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookUpdateDTO {
    private String title;
    private String author;
    private LocalDate publishedDate;
}
