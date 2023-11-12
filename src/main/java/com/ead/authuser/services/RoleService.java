package com.ead.authuser.services;

import com.ead.authuser.dataprovider.http.role.entity.RoleEntity;
import com.ead.authuser.dataprovider.http.role.entity.enums.RoleTypeEntityEnum;

import java.util.Optional;

public interface RoleService {
    Optional<RoleEntity> findByRoleName(RoleTypeEntityEnum roleType);
}
