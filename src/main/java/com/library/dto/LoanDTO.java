/**
 * Data Transfer Object (DTO) class representing a loan.
 */
package com.library.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) representing a loan transaction.
 */
public class LoanDTO {

    private int id;
    private int bookId;
    private int userId;
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
     * Retrieves the ID of the borrowed book.
     *
     * @return The ID of the borrowed book.
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Sets the ID of the borrowed book.
     *
     * @param bookId The ID of the borrowed book.
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * Retrieves the ID of the user who borrowed the book.
     *
     * @return The ID of the user who borrowed the book.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who borrowed the book.
     *
     * @param userId The ID of the user who borrowed the book.
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
