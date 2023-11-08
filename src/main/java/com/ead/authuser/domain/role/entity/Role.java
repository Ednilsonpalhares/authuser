package com.ead.authuser.domain.role.entity;

import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private UUID roleId;
    private RoleTypeEnum roleName;
}
