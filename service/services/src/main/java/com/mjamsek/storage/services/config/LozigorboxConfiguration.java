package com.mjamsek.storage.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("lozigorbox-config")
@ApplicationScoped
public class LozigorboxConfiguration {
    
    @ConfigValue("data-storage-path")
    private String dataStoragePath;
    
    public String getDataStoragePath() {
        return dataStoragePath;
    }
    
    public void setDataStoragePath(String dataStoragePath) {
        this.dataStoragePath = dataStoragePath;
    }
}
