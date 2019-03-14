package com.mjamsek.storage.api.v1.endpoints;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.dto.MenuEntry;
import com.mjamsek.storage.services.FileService;
import com.mjamsek.storage.services.MenuEntryService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.net.URI;

@RequestScoped
@Path("/files")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FileEndpoint {
    
    @Inject
    private FileService fileService;
    
    @Inject
    private MenuEntryService menuEntryService;
    
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
        @FormDataParam("file") InputStream fileInputStream,
        @FormDataParam("file") FormDataContentDisposition fileMetadata,
        @FormDataParam("parent") long parentId
    ) {
        
        File file = fileService.saveFile(fileInputStream, fileMetadata);
        MenuEntry menuEntry = menuEntryService.createNewFileEntry(file, parentId);
        
        String baseUrl = ConfigurationUtil.getInstance().get("kumuluzee.server.base-url").get();
        URI entityUri = URI.create(baseUrl + "/v1/files/download/" + file.getId());
        
        return Response.created(entityUri).entity(menuEntry).build();
    }
    
    @GET
    @Path("/download/{fileId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("fileId") long fileId) {
        File file = fileService.getFile(fileId);
        byte[] fileByteArray = fileService.getFileByteArray(file.getFilename());
        return Response.ok(fileByteArray)
            .header(HttpHeaders.CONTENT_TYPE, file.getMimeType())
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFilename())
            .build();
    }
    
    @POST
    @Path("/init-storage")
    public Response initializeStorage() {
        boolean dirCreated = fileService.initializeStorageDirectory();
        if (dirCreated) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
    
}
