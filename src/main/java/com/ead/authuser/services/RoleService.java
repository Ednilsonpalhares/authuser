package com.ead.authuser.services;

import com.ead.authuser.dataprovider.database.role.entity.RoleEntity;
import com.ead.authuser.dataprovider.database.role.entity.enums.RoleTypeEntityEnum;

import java.util.Optional;

public interface RoleService {
    Optional<RoleEntity> findByRoleName(RoleTypeEntityEnum roleType);
}
