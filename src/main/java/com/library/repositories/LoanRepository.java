package com.library.repositories;


import com.library.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    List<Loan> findByUserUsername(String username);
}
