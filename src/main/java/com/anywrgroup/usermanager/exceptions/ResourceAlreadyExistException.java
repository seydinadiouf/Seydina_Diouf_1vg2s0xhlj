package com.anywrgroup.usermanager.exceptions;

public class ResourceAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public ResourceAlreadyExistException(String message) {
        super(message);
    }
    
    public ResourceAlreadyExistException(String entity, Object value) {
        super(entity + " already exit for user with id : " + value);
    }
    public ResourceAlreadyExistException(String entity, String reference) {
        super(entity + " already exit for version reference : " + reference);
    }

    public ResourceAlreadyExistException(String entity, String originOrTypeOrAccount, String reference) {
        super(entity + " already exists for origin/type/account: " + originOrTypeOrAccount + " and reference : " + reference);
    }
    
}
