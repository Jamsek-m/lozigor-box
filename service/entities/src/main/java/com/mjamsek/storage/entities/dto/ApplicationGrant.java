package com.mjamsek.storage.entities.dto;

public class ApplicationGrant {
    
    private String accessToken;
    
    private int expiresIn;
    
    private int sessionExpiresIn;
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public int getExpiresIn() {
        return expiresIn;
    }
    
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
    
    public int getSessionExpiresIn() {
        return sessionExpiresIn;
    }
    
    public void setSessionExpiresIn(int sessionExpiresIn) {
        this.sessionExpiresIn = sessionExpiresIn;
    }
}
