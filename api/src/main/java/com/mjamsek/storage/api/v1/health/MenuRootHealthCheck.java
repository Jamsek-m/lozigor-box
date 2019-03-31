package com.mjamsek.storage.api.v1.health;

import com.mjamsek.storage.entities.dto.MenuEntry;
import com.mjamsek.storage.services.MenuEntryService;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class MenuRootHealthCheck implements HealthCheck {
    
    private static final String HEALTHCHECK_NAME = "MenuRootExistsHealthCheck";
    
    @Inject
    private MenuEntryService menuEntryService;
    
    @Override
    public HealthCheckResponse call() {
        MenuEntry rootElement = menuEntryService.getRootElement();
        if (rootElement != null) {
            return HealthCheckResponse.named(HEALTHCHECK_NAME).up().build();
        }
        return HealthCheckResponse.named(HEALTHCHECK_NAME).down().build();
    }
}
