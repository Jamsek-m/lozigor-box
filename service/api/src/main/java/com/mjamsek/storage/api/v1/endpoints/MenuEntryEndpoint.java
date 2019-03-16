package com.mjamsek.storage.api.v1.endpoints;

import com.mjamsek.storage.api.v1.constants.CustomHttpHeader;
import com.mjamsek.storage.entities.dto.Directory;
import com.mjamsek.storage.entities.dto.MenuEntry;
import com.mjamsek.storage.services.MenuEntryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuEntryEndpoint {
    
    @Inject
    private MenuEntryService menuEntryService;
    
    @GET
    @Path("/{parentId}")
    public Response getMenuLevel(@PathParam("parentId") long parentId) {
        List<MenuEntry> menuEntries = menuEntryService.getChildrenOf(parentId);
        return Response.ok(menuEntries).header(CustomHttpHeader.X_TOTAL_COUNT, menuEntries.size()).build();
    }
    
    @POST
    public Response createDirectory(Directory directory) {
        MenuEntry newDir = menuEntryService.createDirectory(directory);
        return Response.status(Response.Status.CREATED).entity(newDir).build();
    }
    
    @POST
    @Path("/init-root")
    public Response initializeRootMenuItem() {
        boolean created = menuEntryService.createRootElement();
        if (created) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
