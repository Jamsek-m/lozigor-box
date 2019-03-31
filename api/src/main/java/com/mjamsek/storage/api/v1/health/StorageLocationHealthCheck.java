package com.mjamsek.storage.api.v1.health;

import com.mjamsek.storage.services.config.LozigorboxConfiguration;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Paths;

@Health
@ApplicationScoped
public class StorageLocationHealthCheck implements HealthCheck {
    
    private static final String HEALTHCHECK_NAME = "StorageLocationExistsHealthCheck";
    
    @Inject
    private LozigorboxConfiguration configuration;
    
    @Override
    public HealthCheckResponse call() {
        if (Files.exists(Paths.get(configuration.getDataStoragePath()))) {
            return HealthCheckResponse.named(HEALTHCHECK_NAME).up().build();
        }
        return HealthCheckResponse.named(HEALTHCHECK_NAME).down().build();
    }
}
