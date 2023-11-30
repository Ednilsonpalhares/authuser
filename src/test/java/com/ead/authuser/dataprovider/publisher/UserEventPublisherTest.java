package com.ead.authuser.dataprovider.publisher;

import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.dataprovider.http.user.entity.ActionTypeEnum;
import com.ead.authuser.dataprovider.publisher.entity.UserNotificationEntity;
import com.ead.authuser.dataprovider.publisher.mapper.UserNotificationMapper;
import com.ead.authuser.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEventPublisherTest {

    private static final String EXCHANGE_USER_EVENT = "ead.userevent";
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private UserNotificationMapper userNotificationMapper;
    @InjectMocks
    private UserEventPublisher userEventPublisher;

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(ActionTypeEnum.CREATE),
                Arguments.of(ActionTypeEnum.UPDATE),
                Arguments.of(ActionTypeEnum.DELETE)
        );
    }

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userEventPublisher, "exchangeUserEvent", EXCHANGE_USER_EVENT);
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void shouldPublishUser(final ActionTypeEnum actionTypeEnum) {

        final User user = TestUtils.EASY_RANDOM.nextObject(User.class);
        final UserNotificationEntity userNotificationEntity = TestUtils.EASY_RANDOM.nextObject(UserNotificationEntity.class);
        userNotificationEntity.setActionType(actionTypeEnum.name());

        when(userNotificationMapper.userToUserNotificationEntity(any(), any()))
                .thenReturn(userNotificationEntity);

        userEventPublisher.publishUserNotification(user, actionTypeEnum);

        verify(rabbitTemplate, times(1)).
                convertAndSend(EXCHANGE_USER_EVENT, "", userNotificationEntity);
    }
}