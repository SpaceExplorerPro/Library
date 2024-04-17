package com.library.repositories;

import com.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for User entities, extending JpaRepository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a user by username.
     *
     * @param username The username to search for.
     * @return An Optional containing the found User entity, or empty if not found.
     */
    Optional<User> findByUsername(String username);
}
