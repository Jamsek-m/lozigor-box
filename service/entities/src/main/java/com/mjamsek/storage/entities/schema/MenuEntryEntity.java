package com.mjamsek.storage.entities.schema;

import com.mjamsek.storage.entities.enums.MenuEntryType;

import javax.persistence.*;

@Entity
@Table(name = "file_entries")
@NamedQueries({
    @NamedQuery(name = MenuEntryEntity.FIND_BY_PARENT, query = "SELECT f FROM MenuEntryEntity f WHERE f.parent = :parentId")
})
public class MenuEntryEntity {
    
    public static final String FIND_BY_PARENT = "MenuEntry.findByParent";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private long parent;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private MenuEntryType type;
    
    @ManyToOne
    @JoinColumn(name = "file_id")
    private FileEntity file;
    
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
    
    public FileEntity getFile() {
        return file;
    }
    
    public void setFile(FileEntity file) {
        this.file = file;
    }
}
