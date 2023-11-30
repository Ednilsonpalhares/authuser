package com.ead.authuser.dataprovider.publisher.mapper;

import com.ead.authuser.dataprovider.http.user.entity.ActionTypeEnum;
import com.ead.authuser.dataprovider.publisher.entity.UserNotificationEntity;
import com.ead.authuser.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserNotificationMapper {

    UserNotificationEntity userToUserNotificationEntity(User user, ActionTypeEnum actionType);
}
