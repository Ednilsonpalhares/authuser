package com.ead.authuser.domain.user.usecase;

import com.ead.authuser.dataprovider.http.user.entity.ActionTypeEnum;
import com.ead.authuser.dataprovider.publisher.UserEventPublisher;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateUseCase {

    private final UserGateway userGateway;
    private final UserEventPublisher userEventPublisher;

    public void execute(User user) {
        userGateway.delete(user);
        userEventPublisher.publishUserNotification(user, ActionTypeEnum.UPDATE);
    }
}