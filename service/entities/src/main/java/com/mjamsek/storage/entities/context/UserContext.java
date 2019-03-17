package com.mjamsek.storage.entities.context;

import com.mjamsek.storage.entities.dto.Role;

import java.util.Set;

public class UserContext {
    
    private String username;
    
    private long id;
    
    private Set<Role> roles;
    
    private boolean isCreated;
    
    public String getUsername() {
        return username;
    }
    
    public long getUserId() {
        return id;
    }
    
    public Set<Role> getUserRoles() {
        return roles;
    }
    
    public boolean hasContext() {
        return isCreated;
    }
    
    UserContext() {
        this.isCreated = false;
    }
    
    void setUsername(String username) {
        this.username = username;
    }
    
    void setId(long id) {
        this.id = id;
    }
    
    void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    void setIsCreated() {
        isCreated = true;
    }
}
