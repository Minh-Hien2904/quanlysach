package com.example.quanlysach.service.borrowing;

import com.example.quanlysach.entity.Borrowing;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.entity.Book;
import com.example.quanlysach.repository.BorrowingRepository;
import com.example.quanlysach.repository.UserRepository;
import com.example.quanlysach.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository,
                                UserRepository userRepository,
                                BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Borrowing> getUnreturnedBorrowingsByUser(User user) {
        return borrowingRepository.findByUserAndReturnedFalse(user);
    }

    @Override
    public Optional<Borrowing> findUnreturnedBorrowing(User user, Book book) {
        return borrowingRepository.findByUserAndBookAndReturnedFalse(user, book);
    }

    @Override
    public Borrowing saveBorrowing(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Optional<Borrowing> existingBorrow = borrowingRepository.findByUserAndBookAndReturnedFalse(user, book);
        if (existingBorrow.isPresent()) {
            throw new RuntimeException("User is already borrowing this book");
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setDueDate(LocalDate.now().plusDays(14)); // thời hạn mặc định 14 ngày
        borrowing.setReturned(false);

        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        if (borrowing.isReturned()) {
            throw new RuntimeException("This book has already been returned");
        }

        borrowing.setReturned(true);
        borrowing.setReturnDate(LocalDate.now());

        return borrowingRepository.save(borrowing);
    }
}
