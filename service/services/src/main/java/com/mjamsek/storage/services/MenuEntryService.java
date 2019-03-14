package com.mjamsek.storage.services;

import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.dto.MenuEntry;

import java.util.List;

public interface MenuEntryService {
    
    MenuEntry createNewFileEntry(File file, long parentId);
    
    MenuEntry getFileEntry(long fileEntryId);
    
    List<MenuEntry> getChildrenOf(long parentId);
    
    void removeFileEntry(long fileEntryId);
}
