package com.mjamsek.storage.entities.schema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "files")
public class FileEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String filename;
    
    private String ext;
    
    @Column(name = "mime_type")
    private String mimeType;
    
    private long size;
    
    @Column(name = "uploaded_at")
    private Date uploadedAt;
    
    @Column(name = "updated_at")
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
