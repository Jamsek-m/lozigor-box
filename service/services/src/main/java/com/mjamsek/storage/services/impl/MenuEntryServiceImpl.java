package com.mjamsek.storage.services.impl;

import com.mjamsek.storage.entities.dto.Directory;
import com.mjamsek.storage.entities.dto.File;
import com.mjamsek.storage.entities.dto.MenuEntry;
import com.mjamsek.storage.entities.dto.MenuEntryRenameRequest;
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
        TypedQuery<MenuEntryEntity> query = em.createNamedQuery(MenuEntryEntity.FIND_BY_PARENT, MenuEntryEntity.class);
        query.setParameter("parentId", parentId);
        List<MenuEntryEntity> entities = query.getResultList();
        return entities.stream().map(MenuEntryMapper::fromEntity).collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public void removeMenuEntry(long menuEntryId) {
        MenuEntryEntity entity = em.find(MenuEntryEntity.class, menuEntryId);
        if (entity == null) {
            throw new EntityNotFoundException(menuEntryId, MenuEntryEntity.class);
        } else if (entity.getType() == MenuEntryType.FILE) {
            fileService.deleteFile(entity.getFile().getId());
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
    
    @Override
    public MenuEntry renameMenuEntry(long entryId, MenuEntryRenameRequest request) {
        MenuEntryEntity entity = em.find(MenuEntryEntity.class, entryId);
        if (entity == null) {
            throw new EntityNotFoundException(entryId, MenuEntry.class);
        }
        entity.setName(request.getNewMenuEntryName());
        em.merge(entity);
        return MenuEntryMapper.fromEntity(entity);
    }
    
    @Override
    public List<MenuEntry> queryFiles(String query) {
        TypedQuery<MenuEntryEntity> jpaQuery = em.createNamedQuery(MenuEntryEntity.QUERY_FILES, MenuEntryEntity.class);
        jpaQuery.setParameter("query", query);
        return jpaQuery.getResultList()
            .stream()
            .map(MenuEntryMapper::fromEntity)
            .collect(Collectors.toList());
    }
    
    @Override
    public long queryFilesCount(String query) {
        TypedQuery<Long> jpaQuery = em.createNamedQuery(MenuEntryEntity.QUERY_FILES_COUNT, Long.class);
        jpaQuery.setParameter("query", query);
        return jpaQuery.getSingleResult();
    }
}
