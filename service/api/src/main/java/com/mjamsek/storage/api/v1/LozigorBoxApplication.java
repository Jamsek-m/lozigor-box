package com.mjamsek.storage.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.mjamsek.storage.api.v1.endpoints.FileEndpoint;
import com.mjamsek.storage.api.v1.endpoints.MenuEntryEndpoint;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/v1")
@CrossOrigin
public class LozigorBoxApplication extends ResourceConfig {
    
    public LozigorBoxApplication() {
        register(MultiPartFeature.class);
        
        register(FileEndpoint.class);
        register(MenuEntryEndpoint.class);
    }
    
}
