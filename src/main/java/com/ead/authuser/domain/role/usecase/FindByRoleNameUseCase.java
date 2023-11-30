package com.ead.authuser.domain.role.usecase;

import com.ead.authuser.domain.exception.NotFoundException;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.domain.role.gateway.RoleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FindByRoleNameUseCase {

    private final RoleGateway roleGateway;

    public Role execute(final RoleTypeEnum roleType) {
        final Role role = roleGateway.findByRoleName(roleType);
        if (isNull(role)) {
            throw new NotFoundException("Role is Not Found.", NOT_FOUND.value());
        }
        return role;
    }
}
