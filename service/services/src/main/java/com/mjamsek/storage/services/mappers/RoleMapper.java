package com.mjamsek.storage.services.mappers;

import com.mjamsek.storage.entities.dto.Role;
import com.mjamsek.storage.entities.schema.RoleEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleMapper {
    
    public static Role fromEntity(RoleEntity entity) {
        Role role = new Role();
        role.setId(entity.getId());
        role.setCode(entity.getCode());
        role.setName(entity.getName());
        return role;
    }
    
    public static Set<Role> fromEntitySet(Set<RoleEntity> entities) {
        return entities.stream().map(RoleMapper::fromEntity).collect(Collectors.toSet());
    }
    
}
