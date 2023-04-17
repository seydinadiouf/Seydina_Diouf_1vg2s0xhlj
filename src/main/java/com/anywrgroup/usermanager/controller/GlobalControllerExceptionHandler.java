package com.anywrgroup.usermanager.controller;

import com.anywrgroup.usermanager.exceptions.ForbiddenActionException;
import com.anywrgroup.usermanager.exceptions.ResourceAlreadyExistException;
import com.anywrgroup.usermanager.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Error notFound(final ResourceNotFoundException e) {
        final Error error = new Error();
        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());

        return error;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public Error conflict(final ResourceAlreadyExistException e) {

        final Error error = new Error();
        error.setCode(HttpStatus.CONFLICT.value());
        error.setMessage(e.getMessage());

        return error;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenActionException.class)
    public Error conflict(final ForbiddenActionException e) {
        final Error error = new Error();
        error.setCode(HttpStatus.FORBIDDEN.value());
        error.setMessage(e.getMessage());

        return error;
    }


     static class Error {
        private Integer code;
        private String message;
        private List<String> errors;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<String> getErrors() {
            return errors;
        }

        public void setErrors(List<String> errors) {
            this.errors = errors;
        }
    }
}
