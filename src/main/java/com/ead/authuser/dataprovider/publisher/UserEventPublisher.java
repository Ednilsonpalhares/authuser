package com.ead.authuser.dataprovider.publisher;

import com.ead.authuser.dataprovider.http.user.entity.ActionType;
import com.ead.authuser.dataprovider.publisher.entity.UserProducerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Value(value = "${ead.broker.exchange.userEvent}")
  private String exchangeUserEvent;

  public void publishUserEvent(UserProducerEntity userProducerEntity, ActionType actionType) {
    userProducerEntity.setActionType(actionType.toString());
    rabbitTemplate.convertAndSend(exchangeUserEvent, "", userProducerEntity);
  }
}
