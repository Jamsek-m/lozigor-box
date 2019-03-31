package com.mjamsek.storage.api.v1.mappers;

import com.mjamsek.storage.entities.exceptions.ExceptionResponse;
import com.mjamsek.storage.entities.exceptions.auth.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationExceptionMapper implements ExceptionMapper<AuthorizationException> {
    
    @Override
    public Response toResponse(AuthorizationException exception) {
        
        Response.Status status = Response.Status.UNAUTHORIZED;
        ExceptionResponse response = new ExceptionResponse(exception.getMessage(), status.getStatusCode());
        if (exception instanceof ExpiredOrInvalidSessionException) {
        
        } else if (exception instanceof InvalidCredentialsException) {
        
        } else if (exception instanceof InvalidTokenException) {
        
        } else if (exception instanceof MissingRoleRequirementException) {
            status = Response.Status.FORBIDDEN;
        } else {
        
        }
        
        return Response.status(status).entity(response).build();
    }
}
