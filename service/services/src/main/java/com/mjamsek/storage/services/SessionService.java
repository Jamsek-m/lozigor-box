package com.mjamsek.storage.services;

import com.mjamsek.storage.entities.schema.Session;
import com.mjamsek.storage.entities.schema.UserEntity;

import javax.servlet.http.Cookie;
import java.util.List;

public interface SessionService {
    
    Session findBySessionId(String sessionId);
    
    Session findByIp(String ip);
    
    List<Session> findAllUserSessions(long userId);
    
    Session createSession(UserEntity user, String ip);
    
    void deleteSession(String sessionId);
    
    Session refreshSession(Session session);
    
    Cookie generateSessionCookie(Session session);
    
    Cookie generateLogoutCookie();
}
