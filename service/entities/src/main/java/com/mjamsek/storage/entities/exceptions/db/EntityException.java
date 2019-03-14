package com.mjamsek.storage.entities.exceptions.db;

import com.mjamsek.storage.entities.exceptions.LozigorboxException;

public class EntityException extends LozigorboxException {
    
    public EntityException() {
        super();
    }
    
    public EntityException(String message) {
        super(message);
    }
    
    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
