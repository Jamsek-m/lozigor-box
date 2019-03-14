package com.mjamsek.storage.services;

import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.schema.FileEntity;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

public interface FileService {
    
    File saveFile(InputStream fileInputStream, FormDataContentDisposition fileMetadata);
    
    File getFile(long fileId);
    
    File getFile(String filename);
    
    byte[] getFileByteArray(String filename);
    
    FileEntity getFileEntity(long fileId);
    
    boolean initializeStorageDirectory();
    
    void deleteFile(long fileId);
}
