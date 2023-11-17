package com.example.applemarketplace.port.rest;

import com.example.applemarketplace.exception.GoodNotFoundException;
import com.example.applemarketplace.exception.IllegalOrderPaymentException;
import com.example.applemarketplace.exception.OrderNotFoundException;
import com.example.applemarketplace.port.rest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GoodNotFoundException.class)
    public ResponseEntity<ErrorDto> handleGoodNotFoundException(final GoodNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto("Good not found"));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrderNotFoundException(final OrderNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto("Order not found"));
    }

    @ExceptionHandler(IllegalOrderPaymentException.class)
    public ResponseEntity<ErrorDto> handleIllegalOrderPaymentException(final IllegalOrderPaymentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto("This order cannot be payed by you"));
    }
}
