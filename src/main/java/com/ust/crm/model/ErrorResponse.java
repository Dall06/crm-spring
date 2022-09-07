package com.ust.crm.model;

import com.ust.crm.model.builders.ErrorResponseBuilder;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String msg;
    private Map<String, String> errors;
    private String route;

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
