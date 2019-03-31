package com.mjamsek.storage.api.v1.endpoints;

import com.mjamsek.storage.api.v1.constants.CustomHttpHeader;
import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.dto.MenuEntry;
import com.mjamsek.storage.services.FileService;
import com.mjamsek.storage.services.MenuEntryService;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    public Response mutlipleUpload(
        @FormDataParam("file") List<FormDataBodyPart> body,
        @FormDataParam("parent") long parentId
    ) {
        List<MenuEntry> responseEntries = new ArrayList<>();
        for(FormDataBodyPart part : body) {
            ContentDisposition fileMetadata = part.getContentDisposition();
            File file = fileService.saveFile(part.getEntityAs(InputStream.class), fileMetadata);
            MenuEntry menuEntry = menuEntryService.createNewFileEntry(file, fileMetadata.getFileName(), parentId);
            responseEntries.add(menuEntry);
        }
        return Response
            .status(Response.Status.CREATED)
            .entity(responseEntries)
            .header(CustomHttpHeader.X_TOTAL_COUNT, responseEntries.size())
            .build();
    }
    
    @GET
    @Path("/download/{fileId}")
    @Produces({MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON})
    public Response downloadFile(@PathParam("fileId") long fileId) {
        File file = fileService.getFile(fileId);
        byte[] fileByteArray = fileService.getFileByteArray(file.getFilename());
        return Response.ok(fileByteArray)
            .header(HttpHeaders.CONTENT_TYPE, file.getMimeType())
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFilename())
            .build();
    }
    
    @GET
    @Path("/{fileId}")
    public Response getFileMeta(@PathParam("fileId") long fileId) {
        File file = fileService.getFile(fileId);
        return Response.ok(file).build();
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
