package com.example.quanlysach.service;

import com.example.quanlysach.entity.Book;
import com.example.quanlysach.repository.BookRepository;
import com.example.quanlysach.service.book.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    private BookRepository bookRepository;
    private BookServiceImpl bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);

        sampleBook = new Book();
        sampleBook.setTitle("Test Book");
        sampleBook.setAuthor("Author A");
        sampleBook.setCategory("Fiction");
        sampleBook.setPublishYear(2022);
        sampleBook.setStatus("Available");
    }

    @Test
    void testCreateBook() {
        when(bookRepository.save(sampleBook)).thenReturn(sampleBook);

        Book created = bookService.createBook(sampleBook);

        assertEquals("Test Book", created.getTitle());
        verify(bookRepository, times(1)).save(sampleBook);
    }

    @Test
    void testGetAllBooks() {
        List<Book> mockBooks = Arrays.asList(sampleBook);
        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<Book> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById_Found() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.getBookById(1L));
    }

    @Test
    void testUpdateBook_Found() {
        Book updatedInfo = new Book();
        updatedInfo.setTitle("Updated Title");
        updatedInfo.setAuthor("Updated Author");
        updatedInfo.setCategory("Updated Category");
        updatedInfo.setPublishYear(2023);
        updatedInfo.setStatus("Unavailable");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedInfo);

        Book result = bookService.updateBook(1L, updatedInfo);

        assertEquals("Updated Title", result.getTitle());
        verify(bookRepository).save(sampleBook);
    }

    @Test
    void testUpdateBook_NotFound() {
        Book updatedInfo = new Book();
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.updateBook(1L, updatedInfo));
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
