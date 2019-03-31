package com.mjamsek.storage.services;

import com.mjamsek.storage.entities.exceptions.auth.InvalidTokenException;
import com.mjamsek.storage.entities.schema.UserEntity;
import io.jsonwebtoken.Claims;

public interface TokenService {
    
    String generateAccessToken(UserEntity user);
    
    void validateToken(String jwt) throws InvalidTokenException;
    
    Claims getTokenClaims(String jwt) throws InvalidTokenException;
    
}
