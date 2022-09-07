package com.ust.crm.controller.handlers;

import com.ust.crm.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.TreeMap;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = new TreeMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }
        ErrorResponse response = new ErrorResponse();
        response.setErrors(errors);
        response.setRoute(request.getDescription(false).substring(4));
        return handleExceptionInternal(
                exception, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Map<String, String> errors = new TreeMap<>();

        StringBuilder builder = new StringBuilder();
        builder.append("method ");
        builder.append(exception.getMethod());
        builder.append(" not supported, try with: ");

        exception.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        errors.put("error", builder.toString());
        ErrorResponse response = new ErrorResponse();
        response.setErrors(errors);
        response.setRoute(request.getDescription(false).substring(4));

        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
