package com.application.springMvc.controller;

import com.application.springMvc.exceptions.CustomException2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Example of a ControllerAdvice
 * @author Ihor Savchenko
 * @version 1.0
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler2 {

    @ExceptionHandler(value = { RuntimeException.class,
            CustomException2.class })
    protected ResponseEntity<Object> handleConflict() {

        String bodyOfResponse = "This is a body of a response";
        return new ResponseEntity<Object>(bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
