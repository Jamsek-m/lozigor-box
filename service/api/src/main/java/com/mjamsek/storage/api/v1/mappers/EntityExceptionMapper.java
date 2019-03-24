package com.mjamsek.storage.api.v1.mappers;

import com.mjamsek.storage.entities.exceptions.ExceptionResponse;
import com.mjamsek.storage.entities.exceptions.db.EntityException;
import com.mjamsek.storage.entities.exceptions.db.EntityNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityExceptionMapper implements ExceptionMapper<EntityException> {
    
    @Override
    public Response toResponse(EntityException exception) {
        if (exception instanceof EntityNotFoundException) {
            Response.Status status = Response.Status.NOT_FOUND;
            ExceptionResponse response = new ExceptionResponse("Entity not found!", status.getStatusCode());
            return Response.status(status).entity(response).build();
        }
    
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse("Unknown entity error!", status.getStatusCode());
        return Response.status(status).entity(response).build();
    }
}
