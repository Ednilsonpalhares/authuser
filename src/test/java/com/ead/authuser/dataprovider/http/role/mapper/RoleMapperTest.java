package com.ead.authuser.dataprovider.http.role.mapper;

import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.dataprovider.http.role.entity.RoleEntity;
import com.ead.authuser.domain.role.entity.Role;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoleMapperTest {

    private static final EasyRandom EASY_RANDOM = TestUtils.EASY_RANDOM;
    private final RoleMapper mapper = Mappers.getMapper(RoleMapper.class);

    @Test
    void shouldPageCourseEntity() {
        final RoleEntity roleEntity = EASY_RANDOM.nextObject(RoleEntity.class);

        final Role role = mapper.roleEntityToRole(roleEntity);

        assertEquals(roleEntity.getRoleId(), role.getRoleId());
        assertEquals(roleEntity.getRoleName().name(), role.getRoleName().name());
    }
}