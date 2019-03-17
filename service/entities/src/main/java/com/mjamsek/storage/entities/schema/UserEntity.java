package com.mjamsek.storage.entities.schema;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {@Index(name = "UNIQ_USERNAME", columnList = "username", unique = true)})
@NamedQueries({
    @NamedQuery(name = UserEntity.FIND_BY_USERNAME, query = "SELECT u FROM UserEntity u WHERE u.username = :username")
})
public class UserEntity {
    
    public static final String FIND_BY_USERNAME = "User.findByUsername";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    private boolean active;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public Set<RoleEntity> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
