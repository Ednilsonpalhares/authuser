package com.ead.authuser.dataprovider.publisher;

import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.dataprovider.http.user.entity.ActionType;
import com.ead.authuser.dataprovider.publisher.entity.UserProducerEntity;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserEventPublisherTest {

    private static final String EXCHANGE_USER_EVENT = "ead.userevent";
    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private UserEventPublisher userEventPublisher;

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(ActionType.CREATE),
                Arguments.of(ActionType.UPDATE),
                Arguments.of(ActionType.DELETE)
        );
    }

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userEventPublisher, "exchangeUserEvent", EXCHANGE_USER_EVENT);
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void shouldPublishUser(final ActionType actionType) {

        final UserProducerEntity userProducerEntity = TestUtils.EASY_RANDOM.nextObject(UserProducerEntity.class);

        userEventPublisher.publishUserEvent(userProducerEntity, actionType);

        verify(rabbitTemplate, times(1)).
                convertAndSend(EXCHANGE_USER_EVENT, "", userProducerEntity);
    }
}