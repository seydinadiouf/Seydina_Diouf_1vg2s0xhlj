package com.anywrgroup.usermanager.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String entity, Object value) {
        super(entity + " not found with the value : " + value);
    }

    public ResourceNotFoundException(String entity, String originOrTypeOrAccount, String reference) {
        super(entity + " not found with the origin/type/account : " + originOrTypeOrAccount + " and reference : " + reference);
    }
    
}
