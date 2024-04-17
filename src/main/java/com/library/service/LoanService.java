package com.library.service;


import com.library.dto.LoanDTO;
import com.library.entities.Book;
import com.library.entities.Loan;
import com.library.entities.User;
import com.library.exceptions.ElementNotFoundException;
import com.library.exceptions.InvalidBookDataException;
import com.library.exceptions.RegistrationException;
import com.library.repositories.BookRepository;
import com.library.repositories.LoanRepository;
import com.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Loan loanBook(Loan loan) {
        Book bookFromRequest = loan.getBook();
        User userFromRequest = loan.getUser();

        Book bookFromDB = bookRepository.findById(bookFromRequest.getId())
                .orElseThrow(() -> new ElementNotFoundException("Book not found"));

        User userFromDB = userRepository.findById(userFromRequest.getId())
                .orElseThrow(() -> new ElementNotFoundException("User not found"));

        if (!bookFromDB.equals(bookFromRequest)) {
            throw new InvalidBookDataException("The book in the request does not match the book in the database");
        }

        if (!userFromDB.equals(userFromRequest)) {
            throw new RegistrationException("The user in the request does not match the user in the database");
        }

        int availableCopies = bookFromDB.getAvailableCopies();
        if (availableCopies <= 0) {
            throw new IllegalStateException("No available copies of the book");
        }

        bookFromDB.setAvailableCopies(availableCopies - 1);
        bookRepository.save(bookFromDB);

        loan.setBook(bookFromDB);
        loan.setUser(userFromDB);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Integer id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Loan not found"));

        LocalDate localDate = LocalDateTime.now().toLocalDate();
        loan.setReturnDate(localDate);

        Book book = loan.getBook();
        int availableCopies = book.getAvailableCopies();
        book.setAvailableCopies(availableCopies + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public List<LoanDTO> getLoansOfUser(String username) {
        List<Loan> loans = loanRepository.findByUserUsername(username);

        if (loans.isEmpty()) throw new ElementNotFoundException("No loans found for user: " + username);

        return loans.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll(); 
        return loans.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public LoanDTO toDTO(Loan loan) {
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setBookId(loan.getBook().getId());
        loanDTO.setUserId(loan.getUser().getId());
        loanDTO.setBorrowingDate(loan.getBorrowingDate());
        loanDTO.setDueDate(loan.getDueDate());
        loanDTO.setReturnDate(loan.getReturnDate());
        return loanDTO;
    }
}
