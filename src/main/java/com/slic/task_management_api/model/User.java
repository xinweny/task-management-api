package com.slic.task_management_api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Getter
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @ManyToMany 
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    ) 
    private Collection<Role> roles;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPermissions(this.roles));
    }

    private List<String> getPermissions(Collection<Role> roles) {
        List<String> permissions = new ArrayList<>();
        List<Privilege> privileges = new ArrayList<>();
        
        for (Role role: roles) {
            permissions.add(role.getName()); // Add role name as permission
            privileges.addAll(role.getPrivileges()); // Get all privileges from the role
        }

        for (Privilege privilege: privileges) {
            permissions.add(privilege.getName()); // Add privilege name as permission
        }
        return permissions;
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<String> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String permission: permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        return authorities;
    }
}
