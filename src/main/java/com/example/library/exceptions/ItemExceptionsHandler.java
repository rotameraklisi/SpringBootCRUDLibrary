package com.example.library.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemExceptionsHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ItemErrorResponse> handleException(ItemNotFoundException ine){
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ine.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ItemErrorResponse> handleException(Exception ex){
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ItemErrorResponse> handleException(ConstraintViolationException ex){
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage("deneme");
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.CONFLICT);
    }
}
