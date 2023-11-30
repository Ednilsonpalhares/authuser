package com.ead.authuser.dataprovider.publisher;

import com.ead.authuser.dataprovider.http.user.entity.ActionTypeEnum;
import com.ead.authuser.dataprovider.publisher.entity.UserNotificationEntity;
import com.ead.authuser.dataprovider.publisher.mapper.UserNotificationMapper;
import com.ead.authuser.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final UserNotificationMapper userNotificationMapper;

    @Value(value = "${ead.broker.exchange.userEvent}")
    private String exchangeUserEvent;

    public void publishUserNotification(final User user, ActionTypeEnum actionType) {
        final UserNotificationEntity userNotificationEntity =
                userNotificationMapper.userToUserNotificationEntity(user, actionType);
        rabbitTemplate.convertAndSend(exchangeUserEvent, "", userNotificationEntity);
    }
}
