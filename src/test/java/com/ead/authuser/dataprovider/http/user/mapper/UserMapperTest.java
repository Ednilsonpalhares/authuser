package com.ead.authuser.dataprovider.http.user.mapper;

import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.dataprovider.database.user.entity.UserEntity;
import com.ead.authuser.domain.user.entity.User;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    private static final EasyRandom EASY_RANDOM = TestUtils.EASY_RANDOM;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void shouldUserEntity() {
        final User user = EASY_RANDOM.nextObject(User.class);

        final UserEntity userEntity = mapper.userToUserEntity(user);

        assertEqualsUser(user, userEntity);
    }

    @Test
    void shouldUser() {
        final UserEntity userEntity = EASY_RANDOM.nextObject(UserEntity.class);

        final User user = mapper.userEntityToUser(userEntity);

        assertEqualsUser(user, userEntity);
    }

    private void assertEqualsUser(final User user, final UserEntity userEntity) {

        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getUsername(), userEntity.getUsername());
        assertEquals(user.getEmail(), userEntity.getEmail());
        assertEquals(user.getPassword(), userEntity.getPassword());
        assertEquals(user.getFullName(), userEntity.getFullName());
        assertEquals(user.getUserStatus().name(), userEntity.getUserStatus().name());
        assertEquals(user.getUserType().name(), userEntity.getUserType().name());
        assertEquals(user.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(user.getCpf(), userEntity.getCpf());
        assertEquals(user.getImageUrl(), userEntity.getImageUrl());
        assertEquals(user.getCreationDate(), userEntity.getCreationDate());
        assertEquals(user.getLastUpdateDate(), userEntity.getLastUpdateDate());
        assertEquals(user.getRoles().size(), userEntity.getRoles().size());
    }
}