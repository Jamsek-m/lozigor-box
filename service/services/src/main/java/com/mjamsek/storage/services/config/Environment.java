package com.mjamsek.storage.services.config;

import java.util.HashMap;
import java.util.Map;

public enum Environment {
    DEVELOPMENT("dev"),
    PRODUCTION("prod"),
    TEST("test");
    
    private String value;
    
    Environment(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    private static final Map<String, Environment> valuesMap = new HashMap<>();
    
    static {
        for(Environment env : Environment.values()) {
            valuesMap.put(env.value, env);
        }
    }
    
    public static Environment fromValue(String value) {
        return valuesMap.get(value);
    }
}
