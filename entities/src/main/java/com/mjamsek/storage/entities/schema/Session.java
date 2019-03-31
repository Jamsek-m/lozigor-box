package com.mjamsek.storage.entities.schema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sessions", indexes = {
    @Index(name = "UNIQ_SESSION_ID", columnList = "session_id", unique = true)
})
@NamedQueries({
    @NamedQuery(name = Session.FIND_BY_SESSION_ID, query = "SELECT s FROM Session s WHERE s.sessionId = :sessionId"),
    @NamedQuery(name = Session.FIND_BY_IP, query = "SELECT s FROM Session s WHERE s.ip = :ip"),
    @NamedQuery(name = Session.FIND_BY_USER_ID, query = "SELECT s FROM Session s WHERE s.user.id = :userId")
})
public class Session {
    
    public static final String FIND_BY_SESSION_ID = "Session.findBySessionId";
    public static final String FIND_BY_IP = "Session.findByIp";
    public static final String FIND_BY_USER_ID = "Session.findByUserId";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "session_id", unique = true)
    private String sessionId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    @Column()
    private String ip;
    
    @Column(name = "expiration_date")
    private Date expirationDate;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public UserEntity getUser() {
        return user;
    }
    
    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public Date getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
