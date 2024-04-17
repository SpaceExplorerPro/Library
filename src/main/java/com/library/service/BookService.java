package com.library.service;


import com.library.exceptions.ElementAlreadyExistsException;
import com.library.exceptions.InvalidBookDataException;
import com.library.other.DataValidation;
import com.library.dto.BookDTO;
import com.library.entities.Book;
import com.library.exceptions.ElementNotFoundException;
import com.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent())
            throw new ElementAlreadyExistsException("Book already exists");
        validateBook(book);
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        if (bookRepository.findById(id).isEmpty()) throw new ElementNotFoundException("Book not found");
        bookRepository.deleteById(id);
    }

    public Book updateBook(Integer id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Book not found"));

        if (!existingBook.getIsbn().equals(updatedBook.getIsbn())
                && bookRepository.findByIsbn(updatedBook.getIsbn()).isPresent())
            throw new InvalidBookDataException("ISBN number already exists in database");

        validateBook(updatedBook);

        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setPublishYear(updatedBook.getPublishYear());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());

        return bookRepository.save(existingBook);
    }

    public List<BookDTO> findBooksByExample(Book exampleBook) {
        List<Book> books = bookRepository.findAll(Example.of(exampleBook));
        return books.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private void validateBook(Book book) {
        DataValidation.validateBook(book);
    }

    public BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setPublishYear(book.getPublishYear());
        bookDTO.setAvailableCopies(book.getAvailableCopies());
        return bookDTO;
    }
}
