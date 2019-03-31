package com.mjamsek.storage.entities.dto;

public class Directory {
    
    private String directoryName;
    
    private long parentId;
    
    public String getDirectoryName() {
        return directoryName;
    }
    
    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
    
    public long getParentId() {
        return parentId;
    }
    
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
