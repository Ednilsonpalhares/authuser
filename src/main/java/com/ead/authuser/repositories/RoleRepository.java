package com.ead.authuser.repositories;

import com.ead.authuser.dataprovider.http.role.entity.enums.RoleTypeEntityEnum;
import com.ead.authuser.dataprovider.http.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    RoleEntity findByRoleName(RoleTypeEntityEnum roleType);
}
