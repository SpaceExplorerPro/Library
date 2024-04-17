package com.library.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating invalid book data.
 */
public class InvalidBookDataException extends CustomBaseException {

    /**
     * Constructs a new InvalidBookDataException with the specified error message.
     *
     * @param errorMessage the error message
     */
    public InvalidBookDataException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(errorMessage));
    }
}
