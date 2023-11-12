package com.ead.authuser.entrypoint.http.login.mapper;

import com.ead.authuser.domain.login.entity.Login;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.entrypoint.http.login.dto.LoginDto;
import com.ead.authuser.entrypoint.http.user.dto.request.UserRequestDTO;
import com.ead.authuser.entrypoint.http.user.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginMapper {

    Login loginDtoToLogin(LoginDto loginDto);
}
