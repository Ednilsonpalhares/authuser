package com.ead.authuser.domain.role.usecase;

import com.ead.authuser.domain.exception.NotFoundException;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.domain.role.gateway.RoleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FindByRoleNameUseCase {
    
    private final RoleGateway roleGateway;

    public Role execute(RoleTypeEnum roleType){
        Role role = roleGateway.findByRoleName(roleType);
        if (Objects.isNull(role)){
            throw new NotFoundException("Role is Not Found.", NOT_FOUND.value());
        }
        return role;
    }
}
