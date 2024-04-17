package com.library.repositories;

import com.library.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for Loan entities, extending JpaRepository.
 */
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    /**
     * Finds loans by the username of the associated user.
     *
     * @param username The username of the user.
     * @return A list of loans associated with the specified username.
     */
    List<Loan> findByUserUsername(String username);
}
