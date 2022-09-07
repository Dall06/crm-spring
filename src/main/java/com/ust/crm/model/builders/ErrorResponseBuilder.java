package com.ust.crm.model.builders;

import com.ust.crm.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseBuilder {
    private int status;
    private String msg;
    private Map<String, String> errors;
    private String route;

    public ErrorResponseBuilder setStatus(int status) {
        this.status = status;
        return this;
    }

    public ErrorResponseBuilder setHttpStatus(HttpStatus status) {
        this.status = status.value();

        if (status.isError()) {
            this.errors.put("error", status.getReasonPhrase());
        }

        return this;
    }

    public ErrorResponseBuilder setErrors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }

    public ErrorResponseBuilder setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public ErrorResponseBuilder exception(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();

        errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return this;
    }

    public ErrorResponseBuilder setRoute(String route) {
        this.route = route;
        return this;
    }

    public ErrorResponse buildAnError() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(this.status);
        response.setMsg(this.msg);
        response.setErrors(this.errors);
        response.setRoute(this.route);
        return response;
    }

    public ResponseEntity<ErrorResponse> entity() {
        return ResponseEntity.status(this.status).headers(HttpHeaders.EMPTY).body(buildAnError());
    }
}
