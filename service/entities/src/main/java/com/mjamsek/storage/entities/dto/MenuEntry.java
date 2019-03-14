package com.mjamsek.storage.entities.dto;

import com.mjamsek.storage.entities.enums.MenuEntryType;

public class MenuEntry {
    
    private long id;
    private long parent;
    private String name;
    private MenuEntryType type;
    private File file;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getParent() {
        return parent;
    }
    
    public void setParent(long parent) {
        this.parent = parent;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public MenuEntryType getType() {
        return type;
    }
    
    public void setType(MenuEntryType type) {
        this.type = type;
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
}
