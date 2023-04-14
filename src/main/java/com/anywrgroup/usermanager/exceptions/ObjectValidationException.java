package com.anywrgroup.usermanager.exceptions;

public class ObjectValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectValidationException(String message) {
        super(message);
    }

}
