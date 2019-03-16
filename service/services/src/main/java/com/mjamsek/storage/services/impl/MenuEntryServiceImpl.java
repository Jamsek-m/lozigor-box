package com.mjamsek.storage.services.impl;

import com.mjamsek.storage.entities.dto.Directory;
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
import javax.persistence.TypedQuery;
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
    public MenuEntry createNewFileEntry(File file, String originalFilename, long parentId) {
        MenuEntryEntity entity = new MenuEntryEntity();
        entity.setName(originalFilename);
        
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
    
    @Transactional
    @Override
    public boolean createRootElement() {
        MenuEntry existingEntry = this.getRootElement();
        if (existingEntry == null) {
            MenuEntryEntity entity = new MenuEntryEntity();
            entity.setFile(null);
            entity.setName(MenuEntryEntity.ROOT_ELEMENT_NAME);
            entity.setParent(0);
            entity.setType(MenuEntryType.DIR);
            em.persist(entity);
            return true;
        }
        return false;
    }
    
    @Override
    public MenuEntry getRootElement() {
        TypedQuery<MenuEntryEntity> query = em.createNamedQuery(MenuEntryEntity.FIND_ROOT, MenuEntryEntity.class);
        try {
            return MenuEntryMapper.fromEntity(query.getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }
    
    @Transactional
    @Override
    public MenuEntry createDirectory(Directory dir) {
        MenuEntryEntity entity = new MenuEntryEntity();
        entity.setType(MenuEntryType.DIR);
        entity.setParent(dir.getParentId());
        entity.setName(dir.getDirectoryName());
        entity.setFile(null);
        em.persist(entity);
        return MenuEntryMapper.fromEntity(entity);
    }
}
