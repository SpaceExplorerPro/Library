package com.library.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Base exception class with HTTP status and simple response.
 */
public class CustomBaseException extends RuntimeException {
    private HttpStatus httpStatus;
    private SimpleResponse simpleResponse;

    /**
     * Constructs a new CustomBaseException with the specified HTTP status and simple response.
     *
     * @param httpStatus     the HTTP status of the exception
     * @param simpleResponse the simple response containing error details
     */
    public CustomBaseException(HttpStatus httpStatus, SimpleResponse simpleResponse) {
        this.httpStatus = httpStatus;
        this.simpleResponse = simpleResponse;
    }

    /**
     * Gets the HTTP status of the exception.
     *
     * @return the HTTP status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Sets the HTTP status of the exception.
     *
     * @param httpStatus the HTTP status to set
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * Gets the simple response containing error details.
     *
     * @return the simple response
     */
    public SimpleResponse getSimpleResponse() {
        return simpleResponse;
    }

    /**
     * Sets the simple response containing error details.
     *
     * @param simpleResponse the simple response to set
     */
    public void setSimpleResponse(SimpleResponse simpleResponse) {
        this.simpleResponse = simpleResponse;
    }
}
