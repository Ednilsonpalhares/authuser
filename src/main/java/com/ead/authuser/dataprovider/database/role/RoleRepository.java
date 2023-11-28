package com.ead.authuser.dataprovider.database.role;

import com.ead.authuser.dataprovider.database.role.entity.RoleEntity;
import com.ead.authuser.dataprovider.database.role.entity.enums.RoleTypeEntityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    RoleEntity findByRoleName(RoleTypeEntityEnum roleType);
}
