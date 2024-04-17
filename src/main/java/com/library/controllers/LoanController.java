package com.library.controllers;


import com.library.dto.LoanDTO;
import com.library.entities.Loan;
import com.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Loan loanBook(@RequestBody Loan loan) {
        return loanService.loanBook(loan);
    }

    @PatchMapping("/update/{id}")
    public @ResponseBody Loan returnBook(@PathVariable Integer id) {
        return loanService.returnBook(id);
    }

    @GetMapping("/my-loans")
    public List<LoanDTO> getLoansOfUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return loanService.getLoansOfUser(username);
    }

    @GetMapping("/getAll")
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }
}
