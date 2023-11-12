package com.ead.authuser.dataprovider.http.role.mapper;

import com.ead.authuser.dataprovider.http.role.entity.RoleEntity;
import com.ead.authuser.dataprovider.http.role.entity.enums.RoleTypeEntityEnum;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    Role roleEntityToRole(RoleEntity roleEntity);

    RoleTypeEntityEnum mapEnum(RoleTypeEnum roleTypeEnum);
}
