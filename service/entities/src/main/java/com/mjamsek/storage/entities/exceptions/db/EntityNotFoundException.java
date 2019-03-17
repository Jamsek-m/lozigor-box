package com.mjamsek.storage.entities.exceptions.db;

public class EntityNotFoundException extends EntityException {
    
    public EntityNotFoundException(long id, Class entityType) {
        super(String.format("Entity of type '%s' with id: %d not found!", entityType.getSimpleName(), id));
    }
    
    public EntityNotFoundException(String identifier, Class entityType) {
        super(String.format("Entity of type '%s' with id: %s not found!", entityType.getSimpleName(), identifier));
    }
}
