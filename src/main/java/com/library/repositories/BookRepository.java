package com.library.repositories;

import com.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import java.util.Optional;

/**
 * Repository interface for Book entities, extending JpaRepository and QueryByExampleExecutor.
 */
public interface BookRepository extends JpaRepository<Book, Integer>, QueryByExampleExecutor<Book> {

    /**
     * Finds a book by its ISBN.
     *
     * @param isbn The ISBN of the book to find.
     * @return An Optional containing the found Book entity, or empty if not found.
     */
    Optional<Book> findByIsbn(String isbn);
}
