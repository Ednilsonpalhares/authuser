package com.ead.authuser.entrypoint.http.user.mapper;

import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.entrypoint.http.user.dto.request.UserRequestDTO;
import com.ead.authuser.entrypoint.http.user.dto.response.UserResponseDTO;
import java.util.HashSet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = HashSet.class)
public interface UserMapperEntrypoint {

  @Mapping(target = "roles", expression = "java(new HashSet<>())")
  User userRequestDtoToUser(UserRequestDTO userDto);

  UserResponseDTO userToUserResponseDTO(User user);
}
