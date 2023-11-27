package com.ead.authuser.dataprovider.http.role;

import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.dataprovider.http.role.entity.RoleEntity;
import com.ead.authuser.dataprovider.http.role.entity.enums.RoleTypeEntityEnum;
import com.ead.authuser.dataprovider.http.role.mapper.RoleMapper;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleGatewayImplTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RoleMapper roleMapper;
    @InjectMocks
    private RoleGatewayImpl roleGateway;

    @Test
    void shouldReturnRole() {
        final Role roleMocked = TestUtils.EASY_RANDOM.nextObject(Role.class);

        when(roleMapper.mapEnum(any()))
                .thenReturn(RoleTypeEntityEnum.ROLE_USER);
        when(roleRepository.findByRoleName(RoleTypeEntityEnum.ROLE_USER))
                .thenReturn(RoleEntity.builder().build());
        when(roleMapper.roleEntityToRole(any()))
                .thenReturn(roleMocked);

        final Role role = roleGateway.findByRoleName(RoleTypeEnum.ROLE_USER);

        assertEquals(roleMocked.getRoleId(), role.getRoleId());
        assertEquals(roleMocked.getRoleName(), role.getRoleName());

        verify(roleRepository, times(1)).findByRoleName(RoleTypeEntityEnum.ROLE_USER);
    }
}