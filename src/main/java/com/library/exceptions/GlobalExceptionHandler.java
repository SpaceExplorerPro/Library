package com.library.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Global exception handler for handling custom exceptions and access denied exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(403);
    }

    /**
     * Handles ElementAlreadyExistsException.
     * @param exception the exception
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<SimpleResponse> handleElementAlreadyExistsException(CustomBaseException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getSimpleResponse());
    }

    /**
     * Handles RegistrationException.
     * @param exception the exception
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<SimpleResponse> handleRegistrationException(CustomBaseException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getSimpleResponse());
    }

    /**
     * Handles ElementNotFoundException.
     * @param exception the exception
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<SimpleResponse> handleElementNotFoundException(CustomBaseException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getSimpleResponse());
    }

    /**
     * Handles InvalidBookDataException.
     * @param exception the exception
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(InvalidBookDataException.class)
    public ResponseEntity<SimpleResponse> handleInvalidBookDataException(CustomBaseException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getSimpleResponse());
    }
}

