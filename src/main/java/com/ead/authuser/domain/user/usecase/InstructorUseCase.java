package com.ead.authuser.domain.user.usecase;

import com.ead.authuser.dataprovider.http.user.entity.ActionTypeEnum;
import com.ead.authuser.dataprovider.publisher.UserEventPublisher;
import com.ead.authuser.domain.exception.NotFoundException;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.domain.role.usecase.FindByRoleNameUseCase;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.entity.UserTypeEnum;
import com.ead.authuser.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstructorUseCase {

    private final FindByRoleNameUseCase findByRoleNameUseCase;
    private final UserGateway userGateway;
    private final UserEventPublisher userEventPublisher;

    public User execute(final UUID userId) {
        User user = userGateway.findById(userId);
        if (isNull(user)) {
            throw new NotFoundException("User not found", HttpStatus.NOT_FOUND.value());
        }
        user.setUserType(UserTypeEnum.INSTRUCTOR);
        user.getRoles().add(findByRoleNameUseCase.execute(RoleTypeEnum.ROLE_INSTRUCTOR));
        user = userGateway.save(user);
        userEventPublisher.publishUserNotification(user, ActionTypeEnum.UPDATE);
        return user;
    }
}