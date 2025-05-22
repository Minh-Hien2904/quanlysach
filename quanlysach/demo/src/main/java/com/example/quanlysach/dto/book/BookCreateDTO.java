package com.example.quanlysach.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookCreateDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    private LocalDate publishedDate;
}

