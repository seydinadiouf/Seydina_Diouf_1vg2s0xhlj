package com.anywrgroup.usermanager.exceptions;

public class UnexpectedErrorException  extends Exception{
    private static final long serialVersionUID = 1L;

    public UnexpectedErrorException(String message) {
        super(message);
    }
}
