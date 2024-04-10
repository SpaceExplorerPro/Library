package com.library.service;


import com.library.other.DataValidation;
import com.library.dto.BookDTO;
import com.library.entities.Book;
import com.library.exceptions.ElementNotFoundException;
import com.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        validateBook(book);
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        if (bookRepository.findById(id).isEmpty()) throw new ElementNotFoundException("Book not found");
        bookRepository.deleteById(id);
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private void validateBook(Book book) {
        DataValidation.validateBook(book, bookRepository);
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
