package com.mjamsek.storage.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.mjamsek.storage.api.v1.endpoints.AuthEndpoint;
import com.mjamsek.storage.api.v1.endpoints.FileEndpoint;
import com.mjamsek.storage.api.v1.endpoints.MenuEntryEndpoint;
import com.mjamsek.storage.api.v1.endpoints.UserEndpoint;
import com.mjamsek.storage.api.v1.filters.SecurityHeadersFilter;
import com.mjamsek.storage.api.v1.mappers.AuthorizationExceptionMapper;
import com.mjamsek.storage.api.v1.mappers.ValidationExceptionMapper;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/v1")
@CrossOrigin(supportedMethods = "GET,POST,DELETE,PUT,OPTIONS,PATCH")
public class LozigorBoxApplication extends ResourceConfig {
    
    public LozigorBoxApplication() {
        register(MultiPartFeature.class);
        
        register(FileEndpoint.class);
        register(MenuEntryEndpoint.class);
        register(AuthEndpoint.class);
        register(UserEndpoint.class);
        
        register(AuthorizationExceptionMapper.class);
        register(ValidationExceptionMapper.class);
        
        register(SecurityHeadersFilter.class);
    }
    
}
