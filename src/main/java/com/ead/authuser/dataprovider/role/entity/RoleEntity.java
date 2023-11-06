package com.ead.authuser.dataprovider.role.entity;

import com.ead.authuser.dataprovider.role.entity.enums.RoleTypeEntityEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ROLES")
public class RoleEntity implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Column(nullable = false, unique = true, length = 30)
    @Enumerated(EnumType.STRING)
    private RoleTypeEntityEnum roleName;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.roleName.toString();
    }
}
