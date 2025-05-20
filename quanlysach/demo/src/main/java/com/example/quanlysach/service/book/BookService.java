package com.example.quanlysach.service.book;

import com.example.quanlysach.entity.Book;
import java.util.List;

public interface BookService {
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Book getBookById(Long id);
    List<Book> getAllBooks();
}
