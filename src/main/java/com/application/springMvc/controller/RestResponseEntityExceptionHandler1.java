package com.application.springMvc.controller;

import com.application.springMvc.exceptions.CustomException1;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Example of a ControllerAdvice
 * @author Ihor Savchenko
 * @version 1.0
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler1 extends
        ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RuntimeException.class,
            CustomException1.class })
    protected ResponseEntity<Object> handleConflict(CustomException1 ex, WebRequest request) {

        String bodyOfResponse = "This is a body of a response";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

