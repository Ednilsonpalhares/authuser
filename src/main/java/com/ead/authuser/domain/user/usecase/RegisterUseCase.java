package com.ead.authuser.domain.user.usecase;

import com.ead.authuser.dataprovider.http.user.entity.ActionType;
import com.ead.authuser.dataprovider.publisher.UserEventPublisher;
import com.ead.authuser.domain.exception.BusinessException;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.domain.role.usecase.FindByRoleNameUseCase;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.entity.UserStatusEnum;
import com.ead.authuser.domain.user.gateway.UserGateway;
import com.ead.authuser.domain.user.usecase.mapper.UserProducerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUseCase {

    private final UserGateway userGateway;
    private final FindByRoleNameUseCase findByRoleNameUseCase;
    private final PasswordEncoder passwordEncoder;
    private final UserProducerMapper userProducerMapper;
    private final UserEventPublisher userEventPublisher;

    public User execute(User user) {

        log.info("Register user received {} ", user);
        validateUser(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserStatus(UserStatusEnum.ACTIVE);
        user.getRoles().add(findByRoleNameUseCase.execute(RoleTypeEnum.ROLE_USER));
        log.info("salving user {}", user);
        final User userSalve = userGateway.save(user);
        log.info("publisher user {} and action {}", user, ActionType.CREATE);
        userEventPublisher.publishUserEvent(
                userProducerMapper.userToUserProducerEntity(userSalve), ActionType.CREATE);
        return user;
    }

    private void validateUser(User user) {
        if (userGateway.existsByUsername(user.getUsername())) {
            log.error("Username {} is Already Taken ", user.getUsername());
            throw new BusinessException("Username is Already Taken!", CONFLICT.value());
        }
        if (userGateway.existsByEmail(user.getEmail())) {
            log.error("Email {} is Already Taken ", user.getEmail());
            throw new BusinessException("Email is Already Taken!", CONFLICT.value());
        }
    }
}
