package com.library.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating a registration error.
 */
public class RegistrationException extends CustomBaseException {

    /**
     * Constructs a new RegistrationException with the specified error message.
     *
     * @param errorMessage the error message
     */
    public RegistrationException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(errorMessage));
    }
}
