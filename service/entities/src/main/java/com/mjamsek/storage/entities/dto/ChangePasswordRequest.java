package com.mjamsek.storage.entities.dto;

public class ChangePasswordRequest {
    
    private String password_1;
    private String password_2;
    private String oldPassword;
    
    public String getPassword_1() {
        return password_1;
    }
    
    public void setPassword_1(String password_1) {
        this.password_1 = password_1;
    }
    
    public String getPassword_2() {
        return password_2;
    }
    
    public void setPassword_2(String password_2) {
        this.password_2 = password_2;
    }
    
    public String getOldPassword() {
        return oldPassword;
    }
    
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
