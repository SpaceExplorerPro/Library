package com.library.exceptions;

import org.springframework.http.HttpStatus;

public class RegistrationException extends CustomBaseException {
    public RegistrationException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(errorMessage));
    }
}
