package com.mjamsek.storage.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("lozigorbox-config")
@ApplicationScoped
public class LozigorboxConfiguration {
    
    @ConfigValue("data-storage-path")
    private String dataStoragePath;
    
    @ConfigValue("security.jwt.secret-key")
    private String jwtSecretKey;
    
    @ConfigValue("security.jwt.issuer")
    private String jwtIssuer;
    
    @ConfigValue("security.jwt.token-validity")
    private int jwtTokenValidity;
    
    @ConfigValue("security.session.duration")
    private int sessionDuration;
    
    public String getDataStoragePath() {
        return dataStoragePath;
    }
    
    public void setDataStoragePath(String dataStoragePath) {
        this.dataStoragePath = dataStoragePath;
    }
    
    public String getJwtSecretKey() {
        return jwtSecretKey;
    }
    
    public void setJwtSecretKey(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }
    
    public String getJwtIssuer() {
        return jwtIssuer;
    }
    
    public void setJwtIssuer(String jwtIssuer) {
        this.jwtIssuer = jwtIssuer;
    }
    
    public int getJwtTokenValidity() {
        return jwtTokenValidity;
    }
    
    public void setJwtTokenValidity(int jwtTokenValidity) {
        this.jwtTokenValidity = jwtTokenValidity;
    }
    
    public int getSessionDuration() {
        return sessionDuration;
    }
    
    public void setSessionDuration(int sessionDuration) {
        this.sessionDuration = sessionDuration;
    }
}
