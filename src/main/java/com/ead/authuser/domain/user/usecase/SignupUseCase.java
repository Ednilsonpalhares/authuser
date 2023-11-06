package com.ead.authuser.domain.user.usecase;

import static org.springframework.http.HttpStatus.CONFLICT;

import com.ead.authuser.dataprovider.user.entity.UserEntity;
import com.ead.authuser.domain.role.entity.Role;
import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.domain.role.usecase.FindByRoleNameUseCase;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.exception.BusinessException;
import com.ead.authuser.domain.user.entity.UserStatusEnum;
import com.ead.authuser.domain.user.entity.UserTypeEnum;
import com.ead.authuser.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignupUseCase {

    private final UserGateway userGateway;
    private final FindByRoleNameUseCase findByRoleNameUseCase;
    private final PasswordEncoder passwordEncoder;

    public User execute(User user){

        log.info("POST registerUser userDto received {} ", user.toString());
        validateUser(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserStatus(UserStatusEnum.ACTIVE);
        user.setUserType(UserTypeEnum.USER);
        /*user.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));*/
        user.getRoles().add(findByRoleNameUseCase.execute(RoleTypeEnum.ROLE_USER));
        log.info("salving user {}", user);
        return userGateway.save(user);
    }

    private void validateUser(User user){
        if(userGateway.existsByUsername(user.getUsername())){
            log.error("Username {} is Already Taken ", user.getUsername());
            throw new BusinessException("Username is Already Taken!", CONFLICT.value());
        }
        if(userGateway.existsByEmail(user.getEmail())){
            log.error("Email {} is Already Taken ", user.getEmail());
            throw new BusinessException("Email is Already Taken!", CONFLICT.value());
        }
    }
}
