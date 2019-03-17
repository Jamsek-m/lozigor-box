package com.mjamsek.storage.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.mjamsek.storage.entities.dto.ChangePasswordRequest;
import com.mjamsek.storage.entities.dto.CreateUserRequest;
import com.mjamsek.storage.entities.dto.CreateUserResponse;
import com.mjamsek.storage.entities.dto.User;
import com.mjamsek.storage.entities.schema.UserEntity;

import java.util.List;

public interface UserService {
    
    User getUserById(long userId);
    
    User getUserByUsername(String username);
    
    UserEntity getUserEntityByUsername(String username);
    
    List<User> getUsers(QueryParameters query);
    
    long getUsersCount(QueryParameters query);
    
    CreateUserResponse createUser(CreateUserRequest request);
    
    User setUserStatus(long userId, boolean active);
    
    void updateUserPassword(ChangePasswordRequest request, long userId);
    
}
