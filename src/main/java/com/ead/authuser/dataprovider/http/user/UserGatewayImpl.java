package com.ead.authuser.dataprovider.http.user;

import com.ead.authuser.dataprovider.http.user.entity.UserEntity;
import com.ead.authuser.dataprovider.http.user.mapper.UserMapper;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.gateway.UserGateway;
import com.ead.authuser.repositories.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  @Override
  public void delete(User user) {
    userRepository.delete(userMapper.userToUserEntity(user));
  }

  @Transactional
  @Override
  public User update(User user) {
    final UserEntity userEntity = userMapper.userToUserEntity(user);
    final User userCreate = userMapper.userEntityToUser(userRepository.save(userEntity));

    log.debug("user updated with userId {} ", userCreate.getUserId());
    return userCreate;
  }

  @Override
  public Page<User> finAll(Specification<UserEntity> spec, Pageable pageable) {
    return null;
  }

  @Override
  public User findBUuid(UUID userId) {
    return userMapper.optionalUserEntityToUser(userRepository.findById(userId));
  }
}
