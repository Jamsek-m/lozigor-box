package com.mjamsek.storage.entities.exceptions.disk;

import com.mjamsek.storage.entities.exceptions.LozigorboxException;

public class DiskException extends LozigorboxException {
    
    public DiskException() {
        super();
    }
    
    public DiskException(String message) {
        super(message);
    }
    
    public DiskException(String message, Throwable cause) {
        super(message, cause);
    }
}
