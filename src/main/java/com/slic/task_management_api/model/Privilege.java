package com.slic.task_management_api.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "privileges")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Privilege implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    @Override
    public String getAuthority() {
        return id.toString();
    }
}