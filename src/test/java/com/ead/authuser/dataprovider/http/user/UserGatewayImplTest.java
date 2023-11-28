package com.ead.authuser.dataprovider.http.user;

import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.dataprovider.database.user.UserRepository;
import com.ead.authuser.dataprovider.database.user.entity.UserEntity;
import com.ead.authuser.dataprovider.http.user.mapper.UserMapper;
import com.ead.authuser.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGatewayImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserGatewayImpl userGateway;

    @Test
    void shouldReturnUser() {
        final UUID id = UUID.randomUUID();
        final User userMock = TestUtils.EASY_RANDOM.nextObject(User.class);

        when(userRepository.findUserById(id))
                .thenReturn(new UserEntity());
        when(userMapper.userEntityToUser(any()))
                .thenReturn(userMock);

        final User user = userGateway.findById(id);

        assertEquals(userMock.getId(), user.getId());
        assertEquals(userMock.getEmail(), user.getEmail());
        assertEquals(userMock.getCpf(), user.getCpf());
        verify(userRepository, times(1)).findUserById(id);
    }

    @Test
    void shouldCheckIfUserExistsByUsername() {
        final String username = "name";
        when(userRepository.existsByUsername(username))
                .thenReturn(true);

        assertTrue(userGateway.existsByUsername(username));
        verify(userRepository, times(1)).existsByUsername(username);
    }

    @Test
    void shouldCheckIfUserExistsByEmail() {
        final String email = "test@gmail.com";
        when(userRepository.existsByEmail(email))
                .thenReturn(true);

        assertTrue(userGateway.existsByEmail(email));
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void shouldSaveUser() {
        final User userMock = TestUtils.EASY_RANDOM.nextObject(User.class);

        when(userRepository.save(any()))
                .thenReturn(new UserEntity());
        when(userMapper.userEntityToUser(any()))
                .thenReturn(userMock);

        final User user = userGateway.save(userMock);

        assertEquals(userMock.getId(), user.getId());
        assertEquals(userMock.getEmail(), user.getEmail());
        assertEquals(userMock.getCpf(), user.getCpf());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void shouldDeleteUser() {
        final User userMock = TestUtils.EASY_RANDOM.nextObject(User.class);
        final UserEntity userEntity = TestUtils.EASY_RANDOM.nextObject(UserEntity.class);

        when(userMapper.userToUserEntity(any())).thenReturn(userEntity);

        userGateway.delete(userMock);

        verify(userRepository, times(1)).delete(userEntity);
    }
}