package com.mjamsek.storage.api.v1.mappers;

import com.mjamsek.storage.entities.exceptions.ExceptionResponse;
import com.mjamsek.storage.entities.exceptions.validation.ValidationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    
    @Override
    public Response toResponse(ValidationException exception) {
        Response.Status status = Response.Status.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(exception.getMessage(), status.getStatusCode());
        return Response.status(status).entity(response).build();
    }
}
