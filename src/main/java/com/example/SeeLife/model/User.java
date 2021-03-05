package com.example.SeeLife.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PreRemove;

import com.example.SeeLife.validation.ValidPassword;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username must not be blank.")
    private String username;

    @NotBlank(message = "Password must not be blank.")
    @ValidPassword
    private String password;

    private boolean active;
    
    // allows to shape an additional table for storing an enum.
    @ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER)
    // this allows us to create the table "user_role" for roles set that is joined with
    // the current table ("usr" table) using user_id.
    @CollectionTable(name="user_role", joinColumns=@JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING) // we want the enum is stored as a string.
    Set<Role> roles;
    
    public User() {
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    @PreRemove
    public void onDelete() {
        this.roles.clear();
    }
    
    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public Set<Role> getRoles() {
        return this.roles;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive();
    }
}
