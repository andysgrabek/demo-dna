package com.andysgrabek.dna;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.NoSuchAlgorithmException;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchAlgorithmException.class})
    protected ResponseEntity<Object> algorithmNotFound(NoSuchAlgorithmException e, WebRequest request) {
        //a teapot does not know algorithms
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> constraintViolation(ConstraintViolationException e, WebRequest request) {
        return handleExceptionInternal(e, e
                .getConstraintViolations()
                .stream()
                .map(x -> String.format("%s %s", x.getPropertyPath(), x.getMessage()))
                .toList(), HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
