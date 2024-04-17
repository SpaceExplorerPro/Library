/**
 * Service class for managing loans.
 */
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

/**
 * Service class for performing operations related to loans.
 */
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

    /**
     * Loans a book to a user.
     *
     * @param loan The loan object containing information about the book and user.
     * @return The loan object after it has been saved.
     * @throws ElementNotFoundException if the book or user is not found.
     * @throws InvalidBookDataException if the book information in the loan request does not match the book in the database.
     * @throws RegistrationException if the user in the loan request does not match the user in the database.
     * @throws IllegalStateException if there are no available copies of the book.
     */
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

    /**
     * Returns a book that was previously loaned.
     *
     * @param id The ID of the loan to return.
     * @return The loan object after it has been updated.
     * @throws ElementNotFoundException if the loan is not found.
     */
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

    /**
     * Retrieves all loans of a user.
     *
     * @param username The username of the user.
     * @return A list of LoanDTO objects representing the loans of the user.
     * @throws ElementNotFoundException if no loans are found for the user.
     */
    public List<LoanDTO> getLoansOfUser(String username) {
        List<Loan> loans = loanRepository.findByUserUsername(username);

        if (loans.isEmpty()) throw new ElementNotFoundException("No loans found for user: " + username);

        return loans.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Retrieves all loans.
     *
     * @return A list of LoanDTO objects representing all loans.
     */
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Converts a Loan entity to a LoanDTO object.
     *
     * @param loan The Loan entity to convert.
     * @return A LoanDTO object representing the Loan entity.
     */
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
