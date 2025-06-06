package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.BookRequest;
import com.example.quanlysach.dto.response.BookResponse;
import com.example.quanlysach.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Autowired
    private HttpServletRequest request;

    @PreAuthorize("fileRole(#request)")
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
        BookResponse response = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("fileRole(#request)")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest requestDto) {
        BookResponse response = bookService.updateBook(id, requestDto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("fileRole(#request)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("fileRole(#request)")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        BookResponse response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("fileRole(#request)")
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
