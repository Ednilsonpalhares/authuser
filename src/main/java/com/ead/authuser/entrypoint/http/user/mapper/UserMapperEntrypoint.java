package com.ead.authuser.entrypoint.http.user.mapper;

import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.entity.UserStatusEnum;
import com.ead.authuser.entrypoint.http.user.dto.request.UserRequestDTO;
import com.ead.authuser.entrypoint.http.user.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapperEntrypoint {

    @Mapping(target = "user.userStatus", defaultValue = "ACTIVE")
    User userDtoToUser(UserRequestDTO userDto);
    UserResponseDTO userToUserResponseDTO(User user);
}
