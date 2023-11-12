package com.ead.authuser.domain.user.usecase.mapper;

import com.ead.authuser.dataprovider.publisher.entity.UserProducerEntity;
import com.ead.authuser.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProducerMapper {

    UserProducerEntity userToUserProducerEntity(User user);
}
