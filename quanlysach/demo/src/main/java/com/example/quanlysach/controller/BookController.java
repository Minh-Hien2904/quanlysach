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

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        BookResponse response = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        BookResponse response = bookService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        BookResponse response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
