package com.mjamsek.storage.services.mappers;

import com.mjamsek.storage.entities.dto.CreateUserResponse;
import com.mjamsek.storage.entities.dto.User;
import com.mjamsek.storage.entities.schema.UserEntity;

public class UserMapper {
    
    public static User fromEntity(UserEntity entity) {
        User user = new User();
        user.setActive(entity.isActive());
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setRoles(RoleMapper.fromEntitySet(entity.getRoles()));
        return user;
    }
    
    public static CreateUserResponse createResponse(UserEntity entity, String unhashedPassword) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setPassword(unhashedPassword);
        return response;
    }
}
