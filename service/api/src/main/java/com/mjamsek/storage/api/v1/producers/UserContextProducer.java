package com.mjamsek.storage.api.v1.producers;

import com.mjamsek.storage.entities.context.UserContext;
import com.mjamsek.storage.entities.context.UserContextBuilder;
import com.mjamsek.storage.entities.dto.Role;
import com.mjamsek.storage.services.AuthService;
import com.mjamsek.storage.services.TokenService;
import com.mjamsek.storage.services.config.SecurityConstants;
import io.jsonwebtoken.Claims;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestScoped
public class UserContextProducer {
    
    @Inject
    private TokenService tokenService;
    
    @Inject
    private AuthService authService;
    
    @Produces
    @RequestScoped
    private UserContext produceUserContext() {
        try {
            String jwt = authService.getJwtFromHeader();
            Claims claims = tokenService.getTokenClaims(jwt);
            
            Set<Role> roles = new HashSet<>((List<Role>) claims.get(SecurityConstants.JWT_CLAIM_ROLES));
            
            return UserContextBuilder.getBuilder()
                .withUserId(Long.parseLong(claims.getSubject()))
                .withUsername((String) claims.get(SecurityConstants.JWT_CLAIM_USERNAME))
                .withRoles(roles)
                .build();
        } catch (Exception exc) {
            exc.printStackTrace();
            return UserContextBuilder.empty();
        }
    }
    
}
