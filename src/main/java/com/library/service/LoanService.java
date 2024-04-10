package com.library.service;


import com.library.dto.LoanDTO;
import com.library.entities.Loan;
import com.library.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan loanBook(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan returnBook(Loan loan) {
        LocalDate localDate = LocalDateTime.now().toLocalDate();
        loan.setReturnDate(localDate);
        return loan;
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
