package com.mjamsek.storage.entities.exceptions.auth;

public class InvalidTokenException extends AuthorizationException {
    
    public InvalidTokenException(String message) {
        super(message);
    }
}
