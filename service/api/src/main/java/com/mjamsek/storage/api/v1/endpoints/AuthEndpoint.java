package com.mjamsek.storage.api.v1.endpoints;

import com.mjamsek.storage.api.v1.constants.CustomHttpHeader;
import com.mjamsek.storage.entities.dto.ApplicationGrant;
import com.mjamsek.storage.entities.dto.ApplicationGrantRequest;
import com.mjamsek.storage.services.AuthService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthEndpoint {
    
    @Inject
    private AuthService authService;
    
    @POST
    @Path("/login")
    public Response getGrant(@Context HttpServletResponse httpResponse, ApplicationGrantRequest grantRequest) {
        ApplicationGrant grant = authService.requestGrant(httpResponse, grantRequest);
        return Response.ok(grant).header(CustomHttpHeader.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true").build();
    }
    
    @POST
    @Path("/token")
    public Response getToken(@Context HttpServletResponse httpResponse) {
        return Response.ok(authService.checkExistingGrant(httpResponse))
            .header(CustomHttpHeader.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
            .build();
    }
    
    @GET
    @Path("/logout")
    public Response logout(@Context HttpServletResponse httpResponse) {
        authService.cancelGrant(httpResponse);
        return Response.ok().header(CustomHttpHeader.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true").build();
    }
    
}
