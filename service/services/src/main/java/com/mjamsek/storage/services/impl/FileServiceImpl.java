package com.mjamsek.storage.services.impl;

import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.exceptions.db.EntityNotFoundException;
import com.mjamsek.storage.entities.exceptions.disk.InitStorageException;
import com.mjamsek.storage.entities.exceptions.file.CorruptedOrMissingFileException;
import com.mjamsek.storage.entities.schema.FileEntity;
import com.mjamsek.storage.services.FileService;
import com.mjamsek.storage.services.config.LozigorboxConfiguration;
import com.mjamsek.storage.services.mappers.FileMapper;
import com.mjamsek.storage.services.utils.FileUtil;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@ApplicationScoped
public class FileServiceImpl implements FileService {
    
    @Inject
    private LozigorboxConfiguration configuration;
    
    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;
    
    @Transactional
    @Override
    public File saveFile(InputStream fileInputStream, FormDataContentDisposition fileMetadata) {
        FileEntity entity = new FileEntity();
        String filename = fileMetadata.getFileName();
        String storeAsFileName = FileUtil.getTimestampedFilename(filename);
        String storagePath = configuration.getDataStoragePath();
        try {
            // 1. save to disk
            FileUtil.saveFileToDisk(fileInputStream, storagePath, storeAsFileName);
            
            // 2. save file metadata in db
            entity.setFilename(storeAsFileName);
            entity.setUploadedAt(new Date());
            entity.setUpdatedAt(new Date());
            entity.setSize(FileUtil.getFileSize(storagePath, storeAsFileName));
            entity.setExt(FileUtil.getFileExtension(storeAsFileName));
            entity.setMimeType(FileUtil.getMimeType(fileInputStream));
            em.persist(entity);
            
            return FileMapper.fromEntity(entity);
            
        } catch (Exception ioExc) {
            FileUtil.cleanupFileOnDisk(storagePath, storeAsFileName);
            this.cleanupFileInDb(entity);
            return null;
        }
    }
    
    @Override
    public void deleteFile(long fileId) {
    
    }
    
    @Override
    public File getFile(long fileId) {
        FileEntity entity = em.find(FileEntity.class, fileId);
        if (entity == null) {
            throw new EntityNotFoundException(fileId, FileEntity.class);
        }
        return FileMapper.fromEntity(entity);
    }
    
    @Override
    public File getFile(String filename) {
        return null;
    }
    
    @Override
    public byte[] getFileByteArray(String filename) {
        try {
            return FileUtil.getFileByteArray(configuration.getDataStoragePath(), filename);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CorruptedOrMissingFileException("Error reading file into byte array! File is corrupted or missing.");
        }
    }
    
    @Override
    public FileEntity getFileEntity(long fileId) {
        FileEntity entity = em.find(FileEntity.class, fileId);
        if (entity == null) {
            throw new EntityNotFoundException(fileId, FileEntity.class);
        }
        return entity;
    }
    
    @Override
    public boolean initializeStorageDirectory() {
        java.nio.file.Path storagePath = Paths.get(configuration.getDataStoragePath());
        if (!Files.exists(storagePath)) {
            try {
                Files.createDirectory(storagePath);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                throw new InitStorageException();
            }
        }
        return false;
    }
    
    
    private void cleanupFileInDb(FileEntity entity) {
        if (entity.getId() != 0) {
            entity = em.find(FileEntity.class, entity.getId());
            if (entity != null) {
                em.remove(entity);
            }
        }
    }
}
