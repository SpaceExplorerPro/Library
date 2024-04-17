package com.library.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that an element was not found.
 */
public class ElementNotFoundException extends CustomBaseException {

    /**
     * Constructs a new ElementNotFoundException with the specified error message.
     *
     * @param errorMessage the error message
     */
    public ElementNotFoundException(String errorMessage) {
        super(HttpStatus.NOT_FOUND, new SimpleResponse(errorMessage));
    }
}

