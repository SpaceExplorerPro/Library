package com.library.exceptions;

import org.springframework.http.HttpStatus;

public class CustomBaseException extends RuntimeException {
    private HttpStatus httpStatus;
    private SimpleResponse simpleResponse;

    public CustomBaseException(HttpStatus httpStatus, SimpleResponse simpleResponse) {
        this.httpStatus = httpStatus;
        this.simpleResponse = simpleResponse;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public SimpleResponse getSimpleResponse() {
        return simpleResponse;
    }

    public void setSimpleResponse(SimpleResponse simpleResponse) {
        this.simpleResponse = simpleResponse;
    }
}
