package com.mjamsek.storage.services.impl;

import com.mjamsek.storage.entities.schema.Session;
import com.mjamsek.storage.entities.schema.UserEntity;
import com.mjamsek.storage.services.SessionService;
import com.mjamsek.storage.services.config.Environment;
import com.mjamsek.storage.services.config.SecurityConstants;
import com.mjamsek.storage.services.config.ServerConfiguration;
import com.mjamsek.storage.services.utils.DateUtil;
import com.mjamsek.storage.services.utils.StringUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Cookie;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SessionServiceImpl implements SessionService {
    
    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;
    
    @Inject
    private ServerConfiguration serverConfiguration;
    
    @Override
    public Session findBySessionId(String sessionId) {
        TypedQuery<Session> query = em.createNamedQuery(Session.FIND_BY_SESSION_ID, Session.class);
        query.setParameter("sessionId", sessionId);
        try {
            return query.getSingleResult();
        } catch (NoResultException exc) {
            return null;
        }
    }
    
    @Override
    public Session findByIp(String ip) {
        TypedQuery<Session> query = em.createNamedQuery(Session.FIND_BY_IP, Session.class);
        query.setParameter("ip", ip);
        try {
            return query.getSingleResult();
        } catch (NoResultException exc) {
            return null;
        }
    }
    
    @Override
    public List<Session> findAllUserSessions(long userId) {
        TypedQuery<Session> query = em.createNamedQuery(Session.FIND_BY_USER_ID, Session.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @Transactional
    @Override
    public Session createSession(UserEntity user, String ip) {
        Session session = new Session();
        session.setIp(ip);
        session.setUser(user);
        session.setSessionId(StringUtil.generateRandomString(15));
        session.setExpirationDate(DateUtil.getDateNSecondsFromNow(SecurityConstants.SESSION_DURATION_SECONDS));
        em.persist(session);
        return session;
    }
    
    @Transactional
    @Override
    public void deleteSession(String sessionId) {
        Session session = this.findBySessionId(sessionId);
        if (session != null) {
            em.remove(session);
        }
    }
    
    @Transactional
    @Override
    public Session refreshSession(Session session) {
        UserEntity user = session.getUser();
        String ip = session.getIp();
        
        this.deleteSession(session.getSessionId());
        em.flush();
        return this.createSession(user, ip);
    }
    
    @Override
    public Cookie generateSessionCookie(Session session) {
        return this.generateCookie(session.getSessionId(), SecurityConstants.SESSION_DURATION_SECONDS);
    }
    
    @Override
    public Cookie generateLogoutCookie() {
        return this.generateCookie("LOGOUT", 0);
    }
    
    private Cookie generateCookie(String cookieValue, int maxAge) {
        Cookie cookie = new Cookie(SecurityConstants.COOKIE_SESSION_NAME, cookieValue);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
    
        Environment env = Environment.fromValue(serverConfiguration.getEnv());
        if (env != Environment.DEVELOPMENT) {
            cookie.setSecure(true);
        }
        return cookie;
    }
}
