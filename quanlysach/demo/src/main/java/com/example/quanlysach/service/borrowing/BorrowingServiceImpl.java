package com.example.quanlysach.service.borrowing;

import com.example.quanlysach.dto.response.BorrowingResponse;
import com.example.quanlysach.entity.Borrowing;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.entity.Book;
import com.example.quanlysach.mapper.BorrowingMapper;
import com.example.quanlysach.repository.BorrowingRepository;
import com.example.quanlysach.repository.UserRepository;
import com.example.quanlysach.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowingMapper borrowingMapper;

    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository,
                                UserRepository userRepository,
                                BookRepository bookRepository,
                                BorrowingMapper borrowingMapper) {
        this.borrowingRepository = borrowingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowingMapper = borrowingMapper;
    }

    @Override
    public List<BorrowingResponse> getUnreturnedBorrowingsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        List<Borrowing> borrowings = borrowingRepository.findByUserAndReturnedFalse(user);

        return borrowings.stream()
                .map(borrowingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingResponse findUnreturnedBorrowing(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        Optional<Borrowing> borrowingOpt = borrowingRepository.findByUserAndBookAndReturnedFalse(user, book);
        return borrowingOpt.map(borrowingMapper::toResponse).orElse(null);
    }

    @Override
    public BorrowingResponse borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        Optional<Borrowing> existingBorrow = borrowingRepository.findByUserAndBookAndReturnedFalse(user, book);
        if (existingBorrow.isPresent()) {
            throw new RuntimeException("User is already borrowing this book");
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setDueDate(LocalDate.now().plusDays(14));
        borrowing.setReturned(false);

        Borrowing saved = borrowingRepository.save(borrowing);
        return borrowingMapper.toResponse(saved);
    }

    @Override
    public BorrowingResponse returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        if (borrowing.isReturned()) {
            throw new RuntimeException("This book has already been returned");
        }

        borrowing.setReturned(true);
        borrowing.setReturnDate(LocalDate.now());

        Borrowing saved = borrowingRepository.save(borrowing);
        return borrowingMapper.toResponse(saved);
    }
}
