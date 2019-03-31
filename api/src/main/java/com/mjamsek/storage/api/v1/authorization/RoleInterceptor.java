package com.mjamsek.storage.api.v1.authorization;

import com.mjamsek.storage.services.AuthService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@UserWithRole
public class RoleInterceptor {
    
    @Inject
    private AuthService authService;
    
    @AroundInvoke
    public Object checkRoles(InvocationContext context) throws Exception {
        UserWithRole userWithRoleAnnotation = context.getMethod().getAnnotation(UserWithRole.class);
        
        if (userWithRoleAnnotation != null) {
            authService.validateRequiredRoles(userWithRoleAnnotation.value());
        }
        
        return context.proceed();
    }
    
}
