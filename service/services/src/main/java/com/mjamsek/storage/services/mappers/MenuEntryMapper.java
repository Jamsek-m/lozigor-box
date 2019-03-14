package com.mjamsek.storage.services.mappers;

import com.mjamsek.storage.entities.dto.MenuEntry;
import com.mjamsek.storage.entities.schema.MenuEntryEntity;

public class MenuEntryMapper {
    
    public static MenuEntry fromEntity(MenuEntryEntity entity) {
        MenuEntry entry = new MenuEntry();
        entry.setId(entity.getId());
        entry.setParent(entity.getParent());
        entry.setName(entity.getName());
        entry.setType(entity.getType());
        entry.setFile(FileMapper.fromEntity(entity.getFile()));
        return entry;
    }
}
