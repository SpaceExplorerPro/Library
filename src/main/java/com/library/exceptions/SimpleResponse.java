package com.library.exceptions;

/**
 * Simple response class to hold error messages.
 */
public class SimpleResponse {
    private String errorMessage;

    /**
     * Constructor for SimpleResponse.
     * @param errorMessage the error message
     */
    public SimpleResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message.
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
