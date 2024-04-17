/**
 * Controller class responsible for handling book-related operations.
 */
package com.library.controllers;

import com.library.dto.BookDTO;
import com.library.entities.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing book-related endpoints.
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    /**
     * Constructs a BookController with the provided BookService.
     *
     * @param bookService The service responsible for book-related operations.
     */
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Endpoint for adding a new book to the library.
     *
     * @param book The Book object to be added.
     * @return The added Book object.
     */
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    /**
     * Endpoint for deleting a book from the library by its ID.
     *
     * @param id The ID of the book to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

    /**
     * Endpoint for updating information of a book in the library.
     *
     * @param id          The ID of the book to be updated.
     * @param updatedBook The updated Book object.
     * @return The updated Book object.
     */
    @PatchMapping("/update/{id}")
    public @ResponseBody Book updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    /**
     * Endpoint for searching books in the library based on an example book.
     *
     * @param exampleBook The example Book object to search for.
     * @return A list of BookDTO objects matching the search criteria.
     */
    @GetMapping("/search")
    public List<BookDTO> searchBooksByExample(@RequestBody Book exampleBook) {
        return bookService.findBooksByExample(exampleBook);
    }

    /**
     * Endpoint for retrieving all books in the library.
     *
     * @return A list of all BookDTO objects in the library.
     */
    @GetMapping("/getAll")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
