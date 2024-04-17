/**
 * Data Transfer Object (DTO) class representing a book.
 */
package com.library.dto;

/**
 * Data Transfer Object (DTO) representing a book with basic information.
 */
public class BookDTO {

    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer publishYear;
    private Integer availableCopies;

    /**
     * Retrieves the ID of the book.
     *
     * @return The ID of the book.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the book.
     *
     * @param id The ID of the book.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn The ISBN of the book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Retrieves the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the publisher of the book.
     *
     * @return The publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param publisher The publisher of the book.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Retrieves the publish year of the book.
     *
     * @return The publish year of the book.
     */
    public Integer getPublishYear() {
        return publishYear;
    }

    /**
     * Sets the publish year of the book.
     *
     * @param publishYear The publish year of the book.
     */
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * Retrieves the number of available copies of the book.
     *
     * @return The number of available copies of the book.
     */
    public Integer getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Sets the number of available copies of the book.
     *
     * @param availableCopies The number of available copies of the book.
     */
    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
