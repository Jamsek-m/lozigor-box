package com.mjamsek.storage.services.impl;

import com.mjamsek.storage.entities.exceptions.auth.InvalidTokenException;
import com.mjamsek.storage.entities.schema.UserEntity;
import com.mjamsek.storage.services.TokenService;
import com.mjamsek.storage.services.config.LozigorboxConfiguration;
import com.mjamsek.storage.services.config.SecurityConstants;
import com.mjamsek.storage.services.mappers.RoleMapper;
import com.mjamsek.storage.services.utils.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;

@ApplicationScoped
public class TokenServiceImpl implements TokenService {
    
    @Inject
    private LozigorboxConfiguration configuration;
    
    @Override
    public String generateAccessToken(UserEntity user) {
        return Jwts.builder()
            .setIssuer(configuration.getJwtIssuer())
            .setSubject(String.valueOf(user.getId()))
            .setExpiration(DateUtil.getDateNSecondsFromNow(configuration.getJwtTokenValidity()))
            .setIssuedAt(new Date())
            .claim(SecurityConstants.JWT_CLAIM_USERNAME, user.getUsername())
            .claim(SecurityConstants.JWT_CLAIM_ROLES, RoleMapper.fromEntitySet(user.getRoles()))
            .signWith(SignatureAlgorithm.HS256, configuration.getJwtSecretKey())
            .compact();
    }
    
    @Override
    public void validateToken(String jwt) throws InvalidTokenException {
        try {
            Jwts.parser().setSigningKey(configuration.getJwtSecretKey()).parseClaimsJws(jwt);
        } catch (JwtException ex) {
            throw new InvalidTokenException(ex.getMessage());
        }
    }
    
    @Override
    public Claims getTokenClaims(String jwt) throws InvalidTokenException {
        try {
            return Jwts.parser().setSigningKey(configuration.getJwtSecretKey()).parseClaimsJws(jwt).getBody();
        } catch (JwtException ex) {
            throw new InvalidTokenException(ex.getMessage());
        }
    }
}
