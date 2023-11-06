package com.ead.authuser.entrypoint.http.user.mapper;

import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.entrypoint.http.user.dto.request.UserRequestDTO;
import com.ead.authuser.entrypoint.http.user.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapperEntrypoint {

    User userDtoToUser(UserRequestDTO userDto);
    UserResponseDTO userToUserResponseDTO(User user);
}
