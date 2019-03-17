package com.mjamsek.storage.entities.context;

import com.mjamsek.storage.entities.dto.Role;

import java.util.Set;

public class UserContextBuilder {
    
    private UserContext context;
    
    public static UserContext empty() {
        UserContext context = new UserContext();
        context.setId(-1);
        return context;
    }
    
    public static UserContextBuilder getBuilder() {
        UserContextBuilder builder = new UserContextBuilder();
        builder.context = new UserContext();
        return builder;
    }
    
    public UserContextBuilder withUserId(long id) {
        this.context.setId(id);
        return this;
    }
    
    public UserContextBuilder withUsername(String username) {
        this.context.setUsername(username);
        return this;
    }
    
    public UserContextBuilder withRoles(Set<Role> roles) {
        this.context.setRoles(roles);
        return this;
    }
    
    public UserContext build() {
        this.context.setIsCreated();
        return this.context;
    }
    
}
