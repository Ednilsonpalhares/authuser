package com.ead.authuser.dataprovider.http.role;

import com.ead.authuser.dataprovider.database.role.RoleRepository;
import com.ead.authuser.dataprovider.database.role.entity.enums.RoleTypeEntityEnum;
import com.ead.authuser.dataprovider.http.role.mapper.RoleMapper;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.domain.role.gateway.RoleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleGatewayImpl implements RoleGateway {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role findByRoleName(RoleTypeEnum roleType) {
        final RoleTypeEntityEnum roleTypeEntity = roleMapper.mapEnum(roleType);
        return roleMapper.roleEntityToRole(roleRepository.findByRoleName(roleTypeEntity));
    }
}
