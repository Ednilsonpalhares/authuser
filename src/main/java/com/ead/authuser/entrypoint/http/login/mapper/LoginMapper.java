package com.ead.authuser.entrypoint.http.login.mapper;

import com.ead.authuser.domain.login.entity.Login;
import com.ead.authuser.entrypoint.http.login.dto.LoginDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginMapper {

    Login loginDtoToLogin(LoginDTO loginDto);
}
