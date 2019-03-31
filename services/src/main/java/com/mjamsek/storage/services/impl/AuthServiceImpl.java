package com.mjamsek.storage.services.impl;

import com.mjamsek.storage.entities.dto.ApplicationGrant;
import com.mjamsek.storage.entities.dto.ApplicationGrantRequest;
import com.mjamsek.storage.entities.dto.Role;
import com.mjamsek.storage.entities.exceptions.auth.ExpiredOrInvalidSessionException;
import com.mjamsek.storage.entities.exceptions.auth.InvalidCredentialsException;
import com.mjamsek.storage.entities.exceptions.auth.MissingRoleRequirementException;
import com.mjamsek.storage.entities.schema.Session;
import com.mjamsek.storage.entities.schema.UserEntity;
import com.mjamsek.storage.services.AuthService;
import com.mjamsek.storage.services.SessionService;
import com.mjamsek.storage.services.TokenService;
import com.mjamsek.storage.services.UserService;
import com.mjamsek.storage.services.config.LozigorboxConfiguration;
import com.mjamsek.storage.services.config.SecurityConstants;
import io.jsonwebtoken.Claims;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.util.Date;
import java.util.Set;

@RequestScoped
public class AuthServiceImpl implements AuthService {
    
    @Inject
    private UserService userService;
    
    @Inject
    private SessionService sessionService;
    
    @Inject
    private TokenService tokenService;
    
    @Inject
    private HttpServletRequest httpRequest;
    
    @Inject
    private LozigorboxConfiguration configuration;
    
    @Override
    public ApplicationGrant checkExistingGrant(HttpServletResponse httpResponse) {
        // if session id is not present, throws exception
        String sessionId = getSessionId(false);
        
        // find stored session
        Session session = sessionService.findBySessionId(sessionId);
        
        if (session == null) {
            // invalid session id, remove current cookie
            httpResponse.addCookie(sessionService.generateLogoutCookie());
            throw new ExpiredOrInvalidSessionException("Invalid session!");
        }
        
        if (!session.getIp().equals(httpRequest.getRemoteAddr())) {
            // session was registered with different ip, remove current cookie
            httpResponse.addCookie(sessionService.generateLogoutCookie());
            throw new ExpiredOrInvalidSessionException("Invalid session!");
        }
        
        if (session.getExpirationDate().before(new Date())) {
            // session is expired
            httpResponse.addCookie(sessionService.generateLogoutCookie());
            sessionService.deleteSession(session.getSessionId());
            throw new ExpiredOrInvalidSessionException("Expired session!");
        }
        
        // refresh session
        Session newSession = sessionService.refreshSession(session);
        
        httpResponse.addCookie(sessionService.generateSessionCookie(newSession));
        
        return createGrant(newSession);
    }
    
    @Override
    public ApplicationGrant requestGrant(HttpServletResponse httpResponse, ApplicationGrantRequest grantRequest) {
        UserEntity user = userService.getUserEntityByUsername(grantRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException("Invalid username or password!");
        }
        if (!BCrypt.checkpw(grantRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password!");
        }
        
        Session session = sessionService.createSession(user, httpRequest.getRemoteAddr());
        
        httpResponse.addCookie(sessionService.generateSessionCookie(session));
        
        return createGrant(session);
    }
    
    @Override
    public void cancelGrant(HttpServletResponse httpResponse) {
        String sessionId = getSessionId(true);
        // if session cookie is not present, don't do anything
        if (sessionId != null) {
            sessionService.deleteSession(sessionId);
            Cookie logoutCookie = sessionService.generateLogoutCookie();
            httpResponse.addCookie(logoutCookie);
        }
    }
    
    @Override
    public void validateAuthentication() {
        String jwt = this.getJwtFromHeader();
        tokenService.validateToken(jwt);
    }
    
    @Override
    public void validateRequiredRoles(String[] requiredRoles) {
        String jwt = this.getJwtFromHeader();
        Claims claims = tokenService.getTokenClaims(jwt);
        Set<Role> userRoles = (Set<Role>) claims.get(SecurityConstants.JWT_CLAIM_ROLES);
        boolean hasRequiredRole = false;
        for(String requiredRole : requiredRoles) {
            hasRequiredRole = userRoles.stream().anyMatch(userRole -> userRole.getCode().equals(requiredRole));
            if (hasRequiredRole) {
                break;
            }
        }
        if (!hasRequiredRole) {
            throw new MissingRoleRequirementException("Missing required role!");
        }
    }
    
    @Override
    public String getJwtFromHeader() {
        String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            throw new InvalidCredentialsException("Access token not provided!");
        }
        return authorizationHeader.replace("Bearer ", "");
    }
    
    private ApplicationGrant createGrant(Session session) {
        String jwt = tokenService.generateAccessToken(session.getUser());
        ApplicationGrant grant = new ApplicationGrant();
        grant.setAccessToken(jwt);
        grant.setExpiresIn(configuration.getJwtTokenValidity());
        grant.setSessionExpiresIn(configuration.getSessionDuration());
        return grant;
    }
    
    private String getSessionId(boolean silentFail) {
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies == null) {
            if (silentFail) {
                return null;
            }
            throw new ExpiredOrInvalidSessionException("No active sessions!");
        }
        Cookie cookie = getSessionCookie(cookies, silentFail);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }
    
    private Cookie getSessionCookie(Cookie[] cookies, boolean silentFail) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(SecurityConstants.COOKIE_SESSION_NAME)) {
                return cookie;
            }
        }
        if (silentFail) {
            return null;
        }
        throw new ExpiredOrInvalidSessionException("No active sessions!");
    }
}
