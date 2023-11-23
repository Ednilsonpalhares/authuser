package com.ead.authuser.domain.role.gateway;

import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;

public interface RoleGateway {

  Role findByRoleName(RoleTypeEnum roleType);
}
