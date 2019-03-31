package com.mjamsek.storage.services;

import com.mjamsek.storage.entities.dto.ApplicationGrant;
import com.mjamsek.storage.entities.dto.ApplicationGrantRequest;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    ApplicationGrant checkExistingGrant(HttpServletResponse httpResponse);
    
    ApplicationGrant requestGrant(HttpServletResponse httpResponse, ApplicationGrantRequest grantRequest);
    
    void cancelGrant(HttpServletResponse httpResponse);
    
    void validateAuthentication();
    
    void validateRequiredRoles(String[] requiredRoles);
    
    String getJwtFromHeader();

}
