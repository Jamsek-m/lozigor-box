package com.mjamsek.storage.services.impl;

import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.dto.MenuEntry;
import com.mjamsek.storage.entities.enums.MenuEntryType;
import com.mjamsek.storage.entities.exceptions.db.EntityNotFoundException;
import com.mjamsek.storage.entities.schema.FileEntity;
import com.mjamsek.storage.entities.schema.MenuEntryEntity;
import com.mjamsek.storage.services.FileService;
import com.mjamsek.storage.services.MenuEntryService;
import com.mjamsek.storage.services.mappers.MenuEntryMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MenuEntryServiceImpl implements MenuEntryService {
    
    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;
    
    @Inject
    private FileService fileService;
    
    @Transactional
    @Override
    public MenuEntry createNewFileEntry(File file, long parentId) {
        MenuEntryEntity entity = new MenuEntryEntity();
        entity.setName(file.getFilename());
        
        FileEntity fileEntity = fileService.getFileEntity(file.getId());
        entity.setFile(fileEntity);
        entity.setParent(parentId);
        entity.setType(MenuEntryType.FILE);
        
        em.persist(entity);
        return MenuEntryMapper.fromEntity(entity);
    }
    
    @Override
    public MenuEntry getFileEntry(long fileEntryId) {
        MenuEntryEntity entity = em.find(MenuEntryEntity.class, fileEntryId);
        if (entity == null) {
            throw new EntityNotFoundException(fileEntryId, MenuEntryEntity.class);
        }
        return MenuEntryMapper.fromEntity(entity);
    }
    
    @Override
    public List<MenuEntry> getChildrenOf(long parentId) {
        Query query = em.createNamedQuery(MenuEntryEntity.FIND_BY_PARENT, MenuEntryEntity.class);
        query.setParameter("parentId", parentId);
        List<MenuEntryEntity> entities = query.getResultList();
        return entities.stream().map(MenuEntryMapper::fromEntity).collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public void removeFileEntry(long fileEntryId) {
        MenuEntryEntity entity = em.find(MenuEntryEntity.class, fileEntryId);
        if (entity == null) {
            throw new EntityNotFoundException(fileEntryId, MenuEntryEntity.class);
        }
        em.remove(entity);
    }
}
