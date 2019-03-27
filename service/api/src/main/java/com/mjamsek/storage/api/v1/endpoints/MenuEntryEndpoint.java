package com.mjamsek.storage.api.v1.endpoints;

import com.mjamsek.storage.api.v1.constants.CustomHttpHeader;
import com.mjamsek.storage.entities.dto.Directory;
import com.mjamsek.storage.entities.dto.MenuEntry;
import com.mjamsek.storage.services.MenuEntryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuEntryEndpoint {
    
    @Inject
    private MenuEntryService menuEntryService;
    
    @Context
    protected UriInfo uriInfo;
    
    @GET
    @Path("/{parentId}")
    public Response getMenuLevel(@PathParam("parentId") long parentId) {
        List<MenuEntry> menuEntries = menuEntryService.getChildrenOf(parentId);
        return Response.ok(menuEntries).header(CustomHttpHeader.X_TOTAL_COUNT, menuEntries.size()).build();
    }
    
    @GET
    @Path("/dir/{id}")
    public Response getDirectoryData(@PathParam("id") long dirId) {
        MenuEntry entry = menuEntryService.getFileEntry(dirId);
        return Response.ok(entry).build();
    }
    
    @GET
    @Path("/query")
    public Response queryFiles(@QueryParam("q") @DefaultValue("") String searchQuery) {
        List<MenuEntry> entries = menuEntryService.queryFiles(searchQuery);
        long queryCount = menuEntryService.queryFilesCount(searchQuery);
        return Response.ok().header(CustomHttpHeader.X_TOTAL_COUNT, queryCount).entity(entries).build();
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
