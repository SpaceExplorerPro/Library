/**
 * Controller class responsible for handling loan-related operations.
 */
package com.library.controllers;

import com.library.dto.LoanDTO;
import com.library.entities.Loan;
import com.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing loan-related endpoints.
 */
@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    /**
     * Constructs a LoanController with the provided LoanService.
     *
     * @param loanService The service responsible for loan-related operations.
     */
    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Endpoint for loaning a book.
     *
     * @param loan The Loan object representing the loan transaction.
     * @return The Loan object representing the loaned book.
     */
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Loan loanBook(@RequestBody Loan loan) {
        return loanService.loanBook(loan);
    }

    /**
     * Endpoint for returning a borrowed book.
     *
     * @param id The ID of the loan to be returned.
     * @return The Loan object representing the returned book.
     */
    @PatchMapping("/update/{id}")
    public @ResponseBody Loan returnBook(@PathVariable Integer id) {
        return loanService.returnBook(id);
    }

    /**
     * Endpoint for retrieving loans of the currently authenticated user.
     *
     * @return A list of LoanDTO objects representing the loans of the user.
     */
    @GetMapping("/my-loans")
    public List<LoanDTO> getLoansOfUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return loanService.getLoansOfUser(username);
    }

    /**
     * Endpoint for retrieving all loans in the system.
     *
     * @return A list of LoanDTO objects representing all loans in the system.
     */
    @GetMapping("/getAll")
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }
}
