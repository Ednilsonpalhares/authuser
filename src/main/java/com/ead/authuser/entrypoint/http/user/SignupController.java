package com.ead.authuser.entrypoint.http.user;

import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.usecase.RegisterUseCase;
import com.ead.authuser.entrypoint.http.user.dto.request.UserRequestDTO;
import com.ead.authuser.entrypoint.http.user.dto.response.UserResponseDTO;
import com.ead.authuser.entrypoint.http.user.mapper.UserEntrypointMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SignupController {

    private final RegisterUseCase registerUserUseCase;
    private final UserEntrypointMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Validated UserRequestDTO userDto) {

        final User user = userMapper.userRequestDtoToUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToUserResponseDTO(registerUserUseCase.execute(user)));
    }
}
