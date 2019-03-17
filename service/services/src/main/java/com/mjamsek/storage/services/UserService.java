package com.mjamsek.storage.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.mjamsek.storage.entities.dto.CreateUserRequest;
import com.mjamsek.storage.entities.dto.CreateUserResponse;
import com.mjamsek.storage.entities.dto.User;
import com.mjamsek.storage.entities.schema.UserEntity;

import java.util.List;

public interface UserService {
    
    public User getUserById(long userId);
    
    public User getUserByUsername(String username);
    
    public UserEntity getUserEntityByUsername(String username);
    
    public List<User> getUsers(QueryParameters query);
    
    public long getUsersCount(QueryParameters query);
    
    public CreateUserResponse createUser(CreateUserRequest request);
    
    public User setUserStatus(long userId, boolean active);
    
    public User updateUser(CreateUserRequest request);
    
}
