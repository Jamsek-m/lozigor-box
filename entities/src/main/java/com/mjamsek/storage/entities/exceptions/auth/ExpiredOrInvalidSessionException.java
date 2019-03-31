package com.mjamsek.storage.entities.exceptions.auth;

public class ExpiredOrInvalidSessionException extends AuthorizationException {
    
    public ExpiredOrInvalidSessionException(String message) {
        super(message);
    }
}
