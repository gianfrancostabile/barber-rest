package com.gfstabile.java.barberrest.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A controller advice in case of handle
 * unhandled exceptions and return a
 * custom message as response.
 *
 * @author G. F. Stabile
 */
@ControllerAdvice
public class RestControllerAdvice {

    /**
     * Returns a custom response body if
     * a validation entity exception happens.
     *
     * @return a custom response body
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Void> handleMethodArgumentNotValidException() {
        return this.buildResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns a custom response body if
     * a the object is null during the controller
     * validation.
     *
     * @return a custom response body
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Void> handleHttpMessageNotReadableException() {
        return this.buildResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns a custom response body if
     * a the path variable is missing.
     *
     * @return a custom response body
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Void> handleHttpRequestMethodNotSupportedException() {
        return this.buildResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * Creates an returns a {@link ResponseEntity} instance with
     * the given {@link HttpStatus}.
     *
     * @param statusCode the status code
     * @return the {@link ResponseEntity} instance
     */
    private ResponseEntity<Void> buildResponseEntity(HttpStatus statusCode) {
        return new ResponseEntity<>(statusCode);
    }
}
