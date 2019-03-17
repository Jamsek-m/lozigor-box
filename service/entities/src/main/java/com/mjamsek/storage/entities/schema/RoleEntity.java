package com.mjamsek.storage.entities.schema;

import javax.persistence.*;

@Entity
@Table(name = "roles", indexes = {@Index(name = "UNIQ_CODE", columnList = "code", unique = true)})
@NamedQueries({
    @NamedQuery(name = RoleEntity.FIND_BY_CODE, query = "SELECT r FROM RoleEntity r WHERE r.code = :code")
})
public class RoleEntity {
    
    public static final String FIND_BY_CODE = "Role.findByCode";
    
    public static final String USER_ROLE_CODE = "USER";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    private String code;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
}
