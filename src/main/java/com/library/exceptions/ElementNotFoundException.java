package com.library.exceptions;

import org.springframework.http.HttpStatus;

public class ElementNotFoundException extends CustomBaseException {
    public ElementNotFoundException(String errorMessage) {
        super(HttpStatus.NOT_FOUND, new SimpleResponse(errorMessage));
    }
}
