package com.ead.authuser.domain.role.usecase;

import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.domain.exception.NotFoundException;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.domain.role.gateway.RoleGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindByRoleNameUseCaseTest {

    @Mock
    private RoleGateway roleGateway;
    @InjectMocks
    private FindByRoleNameUseCase findByRoleNameUseCase;

    @Test
    void shouldReturnRole() {
        final Role roleMock = TestUtils.EASY_RANDOM.nextObject(Role.class);
        final RoleTypeEnum roleType = RoleTypeEnum.ROLE_USER;

        when(roleGateway.findByRoleName(roleType)).thenReturn(roleMock);

        final Role role = findByRoleNameUseCase.execute(RoleTypeEnum.ROLE_USER);

        assertEquals(roleMock.getRoleId(), role.getRoleId());
        assertEquals(roleMock.getRoleName(), role.getRoleName());
        verify(roleGateway, times(1)).findByRoleName(roleType);
    }

    @Test
    void shouldThrowNotFoundException() {
        final RoleTypeEnum roleType = RoleTypeEnum.ROLE_USER;

        when(roleGateway.findByRoleName(roleType)).thenReturn(null);

        assertThrows(NotFoundException.class,
                () -> findByRoleNameUseCase.execute(roleType));
    }
}