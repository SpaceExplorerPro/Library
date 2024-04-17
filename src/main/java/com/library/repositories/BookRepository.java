package com.library.repositories;


import com.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer>, QueryByExampleExecutor<Book> {
    Optional<Book> findByIsbn(String isbn);
}
