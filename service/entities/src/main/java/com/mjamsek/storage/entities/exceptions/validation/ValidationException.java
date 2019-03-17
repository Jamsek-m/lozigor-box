package com.mjamsek.storage.entities.exceptions.validation;

import com.mjamsek.storage.entities.exceptions.LozigorboxException;

public class ValidationException extends LozigorboxException {
    
    public ValidationException(String message) {
        super(message);
    }
}
