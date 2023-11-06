package com.ead.authuser.dataprovider.user.mapper;

import com.ead.authuser.dataprovider.user.entity.UserEntity;
import com.ead.authuser.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserEntity userToUserEntity(User user);
    User userEntityToUser(UserEntity userEntity);
}