package com.mjamsek.storage.services;

import com.mjamsek.storage.entities.dto.Directory;
import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.dto.MenuEntry;

import java.util.List;

public interface MenuEntryService {
    
    MenuEntry createNewFileEntry(File file, String originalFilename, long parentId);
    
    MenuEntry getFileEntry(long fileEntryId);
    
    List<MenuEntry> getChildrenOf(long parentId);
    
    void removeFileEntry(long fileEntryId);
    
    boolean createRootElement();
    
    MenuEntry getRootElement();
    
    MenuEntry createDirectory(Directory dir);
    
    List<MenuEntry> queryFiles(String query);
    
    long queryFilesCount(String query);
}
