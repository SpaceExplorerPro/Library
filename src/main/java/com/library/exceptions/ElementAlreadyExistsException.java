package com.library.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that an element already exists.
 */
public class ElementAlreadyExistsException extends CustomBaseException {

    /**
     * Constructs a new ElementAlreadyExistsException with the specified error message.
     *
     * @param errorMessage the error message
     */
    public ElementAlreadyExistsException(String errorMessage) {
        super(HttpStatus.CONFLICT, new SimpleResponse(errorMessage));
    }
}

