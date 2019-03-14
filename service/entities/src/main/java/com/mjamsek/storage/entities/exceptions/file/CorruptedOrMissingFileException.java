package com.mjamsek.storage.entities.exceptions.file;

public class CorruptedOrMissingFileException extends FileException {
    
    public CorruptedOrMissingFileException(String message) {
        super(message);
    }
}
