package com.mjamsek.storage.entities.exceptions.auth;

import com.mjamsek.storage.entities.exceptions.LozigorboxException;

public class AuthorizationException extends LozigorboxException {
    
    public AuthorizationException() {
        super();
    }
    
    public AuthorizationException(String message) {
        super(message);
    }
    
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
