package com.mjamsek.storage.api.v1.mappers;

import com.mjamsek.storage.entities.exceptions.ExceptionResponse;
import com.mjamsek.storage.entities.exceptions.file.CorruptedOrMissingFileException;
import com.mjamsek.storage.entities.exceptions.file.FileException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FileExceptionMapper implements ExceptionMapper<FileException> {
    
    @Override
    public Response toResponse(FileException exception) {
        
        if (exception instanceof CorruptedOrMissingFileException) {
            Response.Status status = Response.Status.NOT_FOUND;
            ExceptionResponse response = new ExceptionResponse("File not found on disk!", status.getStatusCode());
            return Response.status(status).entity(response).build();
        }
        
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse("Server error when handling files!", status.getStatusCode());
        return Response.status(status).entity(response).build();
    }
}
