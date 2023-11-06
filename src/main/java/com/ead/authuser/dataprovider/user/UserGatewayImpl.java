package com.ead.authuser.dataprovider.user;

import com.ead.authuser.dataprovider.user.entity.UserEntity;
import com.ead.authuser.dataprovider.user.mapper.UserMapper;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.gateway.UserGateway;
import com.ead.authuser.entrypoint.http.user.mapper.UserMapperEntrypoint;
import com.ead.authuser.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean existsByUsername(String name) {
        log.debug("check if user exists by name {} ", name);
        return userRepository.existsByUsername(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.debug("check if user exists by email {} ", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {

        final UserEntity userEntity = userMapper.userToUserEntity(user);
        final User userCreate = userMapper.userEntityToUser(userRepository.save(userEntity));

        log.debug("user created with userId {} ", userCreate.getUserId());
        return userCreate;
    }
}
