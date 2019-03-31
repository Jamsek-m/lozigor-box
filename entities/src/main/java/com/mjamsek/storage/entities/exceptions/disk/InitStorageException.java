package com.mjamsek.storage.entities.exceptions.disk;

public class InitStorageException extends DiskException {
    
    public InitStorageException() {
        super("Error initializing storage directory!");
    }
}
