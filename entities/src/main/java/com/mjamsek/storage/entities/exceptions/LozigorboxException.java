package com.mjamsek.storage.entities.exceptions;

public class LozigorboxException extends RuntimeException {
    
    public LozigorboxException() {
        super();
    }
    
    public LozigorboxException(String message) {
        super(message);
    }
    
    public LozigorboxException(String message, Throwable cause) {
        super(message, cause);
    }
}
