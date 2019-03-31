package com.mjamsek.storage.entities.exceptions.auth;

public class MissingRoleRequirementException extends AuthorizationException {
    
    public MissingRoleRequirementException(String message) {
        super(message);
    }
}
