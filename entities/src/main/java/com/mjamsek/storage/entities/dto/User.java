package com.mjamsek.storage.entities.dto;

import java.util.Set;

public class User {
    
    private long id;
    private String username;
    private boolean active;
    private Set<Role> roles;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
