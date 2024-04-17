package com.library.other;

import com.library.entities.Book;
import com.library.entities.User;
import com.library.exceptions.InvalidBookDataException;
import com.library.exceptions.RegistrationException;

/**
 * Class for data validation methods related to users and books.
 */
public class DataValidation {

    /**
     * Validates user data.
     *
     * @param user The user to validate.
     * @throws RegistrationException if any of the user's fields are null or blank.
     */
    public static void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isBlank())
            throw new RegistrationException("Username cannot be null or blank");

        if (user.getPassword() == null || user.getPassword().isBlank())
            throw new RegistrationException("Password cannot be null or blank");

        if (user.getEmail() == null || user.getEmail().isBlank())
            throw new RegistrationException("Email cannot be null or blank");

        if (user.getFullName() == null || user.getFullName().isBlank())
            throw new RegistrationException("Full name cannot be null or blank");
    }

    /**
     * Validates book data.
     *
     * @param book The book to validate.
     * @throws InvalidBookDataException if any of the book's fields are null or blank.
     */
    public static void validateBook(Book book) {
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
