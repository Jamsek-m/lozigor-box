package com.mjamsek.storage.api.v1.endpoints;

import com.mjamsek.storage.api.v1.authorization.AuthenticatedUser;
import com.mjamsek.storage.entities.context.UserContext;
import com.mjamsek.storage.entities.dto.CreateUserRequest;
import com.mjamsek.storage.entities.dto.CreateUserResponse;
import com.mjamsek.storage.entities.dto.User;
import com.mjamsek.storage.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@RequestScoped
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEndpoint {
    
    @Inject
    private UserContext userContext;
    
    @Inject
    private UserService userService;
    
    @GET
    @Path("/profile")
    @AuthenticatedUser
    public Response getUserProfile() {
        if (userContext.hasContext()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", userContext.getUserId());
            map.put("username", userContext.getUsername());
            map.put("roles", userContext.getUserRoles());
            return Response.ok(map).build();
        }
        return Response.serverError().build();
    }
    
    @POST
    public Response createNewUser(CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    
}
