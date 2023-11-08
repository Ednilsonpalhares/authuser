package com.ead.authuser.domain.user.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.domain.exception.BusinessException;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.usecase.FindByRoleNameUseCase;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.gateway.UserGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.crypto.password.PasswordEncoder;

@MockitoSettings
class RegisterUserUseCaseTest {

    @Mock
    private UserGateway userGateway;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private FindByRoleNameUseCase findByRoleNameUseCase;
    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    @Test
    void shouldThrowBusinessExceptionWhenExistRegisteredUserName(){
        final User user = User.builder().username("test").build();

        when(userGateway.existsByUsername(user.getUsername()))
                .thenThrow(BusinessException.class);

        assertThrows(BusinessException.class,
                ()-> registerUserUseCase.execute(user));
    }

    @Test
    void shouldThrowBusinessExceptionWhenExistRegisteredEmail(){
        final User user = User.builder().email("test").build();

        when(userGateway.existsByEmail(user.getEmail()))
                .thenThrow(BusinessException.class);

        assertThrows(BusinessException.class,
                ()-> registerUserUseCase.execute(user));
    }

    @Test
    void shouldRegisterUser(){
        final User userMock = TestUtils.EASY_RANDOM.nextObject(User.class);

        when(userGateway.existsByUsername(userMock.getUsername())).thenReturn(false);
        when(userGateway.existsByEmail(userMock.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userMock.getPassword())).thenReturn(userMock.getPassword());
        when(findByRoleNameUseCase.execute(any())).thenReturn(Role.builder().build());
        when(userGateway.save(userMock)).thenReturn(userMock);
        
        final User user = registerUserUseCase.execute(userMock);

        verify(userGateway, times(1)).existsByUsername(userMock.getUsername());
        verify(userGateway, times(1)).existsByEmail(userMock.getEmail());
        verify(passwordEncoder, times(1)).encode(userMock.getPassword());
        verify(findByRoleNameUseCase, times(1)).execute(any());
        verify(userGateway, times(1)).save(userMock);

        assertEquals(userMock.getUsername(), user.getUsername());
        assertEquals(userMock.getCpf(), user.getCpf());
        assertEquals(userMock.getEmail(), user.getEmail());
    }
}
