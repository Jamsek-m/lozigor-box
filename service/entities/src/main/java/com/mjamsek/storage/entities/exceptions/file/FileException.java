package com.mjamsek.storage.entities.exceptions.file;

import com.mjamsek.storage.entities.exceptions.LozigorboxException;

public class FileException extends LozigorboxException {
    
    public FileException() {
        super();
    }
    
    public FileException(String message) {
        super(message);
    }
    
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
