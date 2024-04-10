package com.library.controllers;


import com.library.dto.BookDTO;
import com.library.entities.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/getAll")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
