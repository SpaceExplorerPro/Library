package com.library.other;

import com.library.entities.Book;
import com.library.entities.User;
import com.library.exceptions.InvalidBookDataException;
import com.library.repositories.BookRepository;
import com.library.repositories.UserRepository;
import com.library.exceptions.ElementAlreadyExistsException;
import com.library.exceptions.RegistrationException;

public class DataValidation {

    public static void validateUser(User user, UserRepository userRepository) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new ElementAlreadyExistsException("User already exists");

        if (user.getUsername() == null || user.getUsername().isBlank())
            throw new RegistrationException("Username cannot be null or blank");

        if (user.getPassword() == null || user.getPassword().isBlank())
            throw new RegistrationException("Password cannot be null or blank");

        if (user.getEmail() == null || user.getEmail().isBlank())
            throw new RegistrationException("Email cannot be null or blank");

        if (user.getFullName() == null || user.getFullName().isBlank())
            throw new RegistrationException("Full name cannot be null or blank");
    }

    public static void validateBook(Book book, BookRepository bookRepository) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent())
            throw new ElementAlreadyExistsException("Book already exists");

        if (book.getIsbn() == null || book.getIsbn().isBlank())
            throw new InvalidBookDataException("ISBN cannot be null or blank");

        if (book.getTitle() == null || book.getTitle().isBlank())
            throw new InvalidBookDataException("Title cannot be null or blank");

        if (book.getAuthor() == null || book.getAuthor().isBlank())
            throw new InvalidBookDataException("Author cannot be null or blank");

        if (book.getPublisher() == null || book.getPublisher().isBlank())
            throw new InvalidBookDataException("Publisher cannot be null or blank");

        if (book.getPublishYear() == null)
            throw new InvalidBookDataException("Publish year cannot be null");

        if (book.getAvailableCopies() == null)
            throw new InvalidBookDataException("Available copies cannot be null");
    }
}
