package com.mjamsek.storage.entities.dto;

import java.util.Date;

public class File {
    
    private long id;
    private String filename;
    private String ext;
    private String mimeType;
    private long size;
    private Date uploadedAt;
    private Date updatedAt;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getExt() {
        return ext;
    }
    
    public void setExt(String ext) {
        this.ext = ext;
    }
    
    public String getMimeType() {
        return mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    public long getSize() {
        return size;
    }
    
    public void setSize(long size) {
        this.size = size;
    }
    
    public Date getUploadedAt() {
        return uploadedAt;
    }
    
    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
