package com.mjamsek.storage.services.impl;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.mjamsek.storage.entities.dto.ChangePasswordRequest;
import com.mjamsek.storage.entities.dto.CreateUserRequest;
import com.mjamsek.storage.entities.dto.CreateUserResponse;
import com.mjamsek.storage.entities.dto.User;
import com.mjamsek.storage.entities.exceptions.db.EntityNotFoundException;
import com.mjamsek.storage.entities.exceptions.validation.ValidationException;
import com.mjamsek.storage.entities.schema.RoleEntity;
import com.mjamsek.storage.entities.schema.UserEntity;
import com.mjamsek.storage.services.UserService;
import com.mjamsek.storage.services.mappers.UserMapper;
import com.mjamsek.storage.services.utils.StringUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    
    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;
    
    @Override
    public User getUserById(long userId) {
        UserEntity entity = em.find(UserEntity.class, userId);
        if (entity != null) {
            return UserMapper.fromEntity(entity);
        }
        return null;
    }
    
    @Override
    public User getUserByUsername(String username) {
        UserEntity entity = this.getUserEntityByUsername(username);
        if (entity != null) {
            return UserMapper.fromEntity(entity);
        } else {
            throw new EntityNotFoundException(username, User.class);
        }
    }
    
    @Override
    public UserEntity getUserEntityByUsername(String username) {
        TypedQuery<UserEntity> query = em.createNamedQuery(UserEntity.FIND_BY_USERNAME, UserEntity.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (NoResultException exc) {
            return null;
        }
    }
    
    @Override
    public List<User> getUsers(QueryParameters query) {
        return JPAUtils
            .queryEntities(em, UserEntity.class, query)
            .stream()
            .map(UserMapper::fromEntity)
            .collect(Collectors.toList());
    }
    
    @Override
    public long getUsersCount(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(em, UserEntity.class, query);
    }
    
    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        UserEntity entity = new UserEntity();
        entity.setActive(true);
        entity.setUsername(request.getUsername());
        entity.setRoles(new HashSet<>());
        RoleEntity userRole = this.getUserRole();
        entity.getRoles().add(userRole);
        String generatedPassword = StringUtil.generateRandomString(10);
        entity.setPassword(BCrypt.hashpw(generatedPassword, BCrypt.gensalt()));
        
        em.persist(entity);
        
        return UserMapper.createResponse(entity, generatedPassword);
    }
    
    @Transactional
    @Override
    public User setUserStatus(long userId, boolean active) {
        UserEntity entity = em.find(UserEntity.class, userId);
        if (entity == null) {
            throw new EntityNotFoundException(userId, User.class);
        }
        entity.setActive(active);
        em.merge(entity);
        return UserMapper.fromEntity(entity);
    }
    
    @Transactional
    @Override
    public void updateUserPassword(ChangePasswordRequest request, long userId) {
        
        if (request.getOldPassword().isEmpty() || request.getPassword_1().isEmpty() || request.getPassword_2().isEmpty()) {
            throw new ValidationException("All fields must be filled!");
        }
        if (!request.getPassword_1().equals(request.getPassword_2())) {
            throw new ValidationException("Password confirmation is not the same as new password!");
        }
        UserEntity entity = em.find(UserEntity.class, userId);
        if (entity == null) {
            throw new EntityNotFoundException(userId, User.class);
        }
        if (!BCrypt.checkpw(request.getOldPassword(), entity.getPassword())) {
            throw new ValidationException("Old password is incorrect!");
        }
        entity.setPassword(BCrypt.hashpw(request.getPassword_1(), BCrypt.gensalt()));
        em.merge(entity);
        em.flush();
    }
    
    private RoleEntity getUserRole() {
        TypedQuery<RoleEntity> query = em.createNamedQuery(RoleEntity.FIND_BY_CODE, RoleEntity.class);
        query.setParameter("code", RoleEntity.USER_ROLE_CODE);
        return query.getSingleResult();
    }
}
