package com.mjamsek.storage.api.v1.authorization;

import com.mjamsek.storage.services.AuthService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@AuthenticatedUser
public class AuthenticationInterceptor {
    
    @Inject
    private AuthService authService;
    
    @AroundInvoke
    public Object checkAuthentication(InvocationContext context) throws Exception {
        
        AuthenticatedUser authenticatedUserAnnotation = context.getMethod().getAnnotation(AuthenticatedUser.class);
        
        if (authenticatedUserAnnotation != null) {
            authService.validateAuthentication();
        }
        
        return context.proceed();
    }
}
