package com.mjamsek.storage.entities.exceptions.auth;

public class InvalidCredentialsException extends AuthorizationException {
    
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
