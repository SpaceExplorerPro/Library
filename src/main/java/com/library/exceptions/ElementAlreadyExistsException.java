package com.library.exceptions;

import org.springframework.http.HttpStatus;

public class ElementAlreadyExistsException extends CustomBaseException {

    public ElementAlreadyExistsException(String errorMessage) {
        super(HttpStatus.CONFLICT, new SimpleResponse(errorMessage));
    }
}
