/**
 * Entity class representing a loan.
 */
package com.library.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity class representing a loan transaction.
 */
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    private LocalDate borrowingDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    /**
     * Retrieves the ID of the loan.
     *
     * @return The ID of the loan.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the loan.
     *
     * @param id The ID of the loan.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the book associated with the loan.
     *
     * @return The Book object associated with the loan.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book associated with the loan.
     *
     * @param book The Book object associated with the loan.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Retrieves the user who borrowed the book.
     *
     * @return The User object representing the user who borrowed the book.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who borrowed the book.
     *
     * @param user The User object representing the user who borrowed the book.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Retrieves the date when the book was borrowed.
     *
     * @return The borrowing date of the book.
     */
    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    /**
     * Sets the date when the book was borrowed.
     *
     * @param borrowingDate The borrowing date of the book.
     */
    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    /**
     * Retrieves the due date for returning the book.
     *
     * @return The due date for returning the book.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for returning the book.
     *
     * @param dueDate The due date for returning the book.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Retrieves the date when the book was returned.
     *
     * @return The return date of the book.
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the date when the book was returned.
     *
     * @param returnDate The return date of the book.
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
