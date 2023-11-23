package com.ead.authuser.domain.role.usecase;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.ead.authuser.domain.role.gateway.RoleGateway;
import com.ead.authuser.domain.exception.NotFoundException;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindByRoleNameUseCase {

  private final RoleGateway roleGateway;

  public Role execute(RoleTypeEnum roleType) {
    Role role = roleGateway.findByRoleName(roleType);
    if (Objects.isNull(role)) {
      throw new NotFoundException("Role is Not Found.", NOT_FOUND.value());
    }
    return role;
  }
}
