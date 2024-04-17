/**
 * Service class for managing books.
 */
package com.library.service;

import com.library.exceptions.ElementAlreadyExistsException;
import com.library.exceptions.ElementNotFoundException;
import com.library.exceptions.InvalidBookDataException;
import com.library.dto.BookDTO;
import com.library.entities.Book;
import com.library.other.DataValidation;
import com.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for performing operations related to books.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a new book to the library.
     *
     * @param book The book to add.
     * @return The added book.
     * @throws ElementAlreadyExistsException if a book with the same ISBN already exists.
     */
    public Book addBook(Book book) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent())
            throw new ElementAlreadyExistsException("Book already exists");
        validateBook(book);
        return bookRepository.save(book);
    }

    /**
     * Deletes a book from the library.
     *
     * @param id The ID of the book to delete.
     * @throws ElementNotFoundException if the book with the given ID is not found.
     */
    public void deleteBook(Integer id) {
        if (bookRepository.findById(id).isEmpty())
            throw new ElementNotFoundException("Book not found");
        bookRepository.deleteById(id);
    }

    /**
     * Updates the information of a book.
     *
     * @param id          The ID of the book to update.
     * @param updatedBook The updated book information.
     * @return The updated book.
     * @throws ElementNotFoundException   if the book with the given ID is not found.
     * @throws InvalidBookDataException    if the updated book data is invalid.
     * @throws ElementAlreadyExistsException if the updated book's ISBN already exists in the database.
     */
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

    /**
     * Finds books that match the example provided.
     *
     * @param exampleBook The example book used for matching.
     * @return A list of BookDTO objects representing the matching books.
     */
    public List<BookDTO> findBooksByExample(Book exampleBook) {
        List<Book> books = bookRepository.findAll(Example.of(exampleBook));
        return books.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Retrieves all books from the library.
     *
     * @return A list of BookDTO objects representing all books in the library.
     */
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Validates the data of a book.
     *
     * @param book The book to validate.
     */
    private void validateBook(Book book) {
        DataValidation.validateBook(book);
    }

    /**
     * Converts a Book entity to a BookDTO object.
     *
     * @param book The Book entity to convert.
     * @return A BookDTO object representing the Book entity.
     */
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
