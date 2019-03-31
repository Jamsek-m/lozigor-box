package com.mjamsek.storage.api.v1.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityHeadersFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        if (response instanceof HttpServletResponse) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.addHeader("X-Content-Type-Options", "nosniff");
            resp.addHeader("X-XSS-Protection", "1; mode=block");
            resp.addHeader("X-Frame-Options", "DENY");
            // TODO: check if appropriate policy
            // resp.addHeader("Content-Security-Policy", "default-src 'self' localhost:8080 *.mjamsek.com fonts.googleapis.com");
            chain.doFilter(request, resp);
        } else {
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy() {
    
    }
}
