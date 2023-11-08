package com.ead.authuser.entrypoint.http.user;

import com.ead.authuser.configs.security.JwtProvider;
import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.usecase.RegisterUserUseCase;
import com.ead.authuser.dtos.JwtDto;
import com.ead.authuser.dtos.LoginDto;
import com.ead.authuser.entrypoint.http.user.dto.request.UserRequestDTO;
import com.ead.authuser.entrypoint.http.user.dto.response.UserResponseDTO;
import com.ead.authuser.entrypoint.http.user.mapper.UserMapperEntrypoint;
import com.ead.authuser.dataprovider.user.entity.UserStatusEntityEnum;
import com.ead.authuser.dataprovider.user.entity.UserTypeEntityEnum;
import com.ead.authuser.dataprovider.role.entity.RoleEntity;
import com.ead.authuser.dataprovider.user.entity.UserEntity;
import com.ead.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final RegisterUserUseCase registerUserUseCase;
    private final UserMapperEntrypoint userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Validated UserRequestDTO userDto){

        final User user = userMapper.userDtoToUser(userDto);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToUserResponseDTO(registerUserUseCase.execute(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtDto(jwt));
    }

    @PostMapping("/signup/admin/usr")
    public ResponseEntity<Object> registerUserAdmin(@RequestBody @Validated
                                                UserRequestDTO userDto){
        log.debug("POST registerUser userDto received {} ", userDto.toString());
        if(userService.existsByUsername(userDto.getUsername())){
            log.warn("Username {} is Already Taken ", userDto.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken!");
        }
        if(userService.existsByEmail(userDto.getEmail())){
            log.warn("Email {} is Already Taken ", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
        }
        RoleEntity roleModel = null;
               /* roleService.findByRoleName(RoleType.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is Not Found."));*/
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var userModel = new UserEntity();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatusEntityEnum.ACTIVE);
        userModel.setUserType(UserTypeEntityEnum.ADMIN);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.getRoles().add(roleModel);
        userService.saveUser(userModel);
        log.debug("POST registerUser userId saved {} ", userModel.getUserId());
        log.info("User saved successfully userId {} ", userModel.getUserId());
        return  ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
}
