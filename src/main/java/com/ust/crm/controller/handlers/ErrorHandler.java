package com.ust.crm.controller.handlers;

import com.ust.crm.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request) {

        return ErrorResponse.builder()
                .exception(ex)
                .setMsg("error when trying to validate the request")
                .setRoute(request.getDescription(false).substring(4))
                .entity();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> msgException(Exception ex, WebRequest request) {
        return ErrorResponse.builder()
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMsg("error when processing the request")
                .setRoute(request.getDescription(false).substring(4))
                .entity();
    }
}
