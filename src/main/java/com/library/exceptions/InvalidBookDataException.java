package com.library.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidBookDataException extends CustomBaseException {
    public InvalidBookDataException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(errorMessage));
    }
}
