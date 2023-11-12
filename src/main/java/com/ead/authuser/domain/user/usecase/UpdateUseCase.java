package com.ead.authuser.domain.user.usecase;

import com.ead.authuser.dataprovider.publisher.UserEventPublisher;
import com.ead.authuser.dataprovider.http.user.entity.ActionType;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.gateway.UserGateway;
import com.ead.authuser.domain.user.usecase.mapper.UserProducerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateUseCase {

    private final UserGateway userGateway;
    private final UserEventPublisher userEventPublisher;
    private final UserProducerMapper userProducerMapper;

    public void execute(User user){
        userGateway.delete(user);
        userEventPublisher.publishUserEvent(userProducerMapper.userToUserProducerEntity(user), ActionType.UPDATE);
    }
}