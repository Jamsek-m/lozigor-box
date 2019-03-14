package com.mjamsek.storage.services.mappers;

import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.schema.FileEntity;

public class FileMapper {
    
    public static File fromEntity(FileEntity entity) {
        File file = new File();
        file.setId(entity.getId());
        file.setFilename(entity.getFilename());
        file.setMimeType(entity.getMimeType());
        file.setExt(entity.getExt());
        file.setSize(entity.getSize());
        file.setUpdatedAt(entity.getUpdatedAt());
        file.setUploadedAt(entity.getUploadedAt());
        return file;
    }
}
