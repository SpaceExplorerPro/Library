package com.library.controllers;


import com.library.dto.LoanDTO;
import com.library.entities.Loan;
import com.library.service.LoanService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/return")
    public @ResponseBody Loan returnBook(@RequestBody Loan loan) {
        return loanService.returnBook(loan);
    }

    @GetMapping("/getAll")
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }
}
