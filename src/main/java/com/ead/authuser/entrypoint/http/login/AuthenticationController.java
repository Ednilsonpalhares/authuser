package com.ead.authuser.entrypoint.http.login;

import com.ead.authuser.domain.login.LoginUseCase;
import com.ead.authuser.domain.login.entity.Login;
import com.ead.authuser.dto.JwtDTO;
import com.ead.authuser.entrypoint.http.login.dto.LoginDTO;
import com.ead.authuser.entrypoint.http.login.mapper.LoginMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class AuthenticationController {

    private final LoginUseCase loginUseCase;
    private final LoginMapper loginMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> authenticate(@Valid @RequestBody LoginDTO loginDto) {

        final Login login = loginMapper.loginDtoToLogin(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(new JwtDTO(loginUseCase.execute(login)));
    }
}
